package com.dmz.service.advisor;

import brave.Span;
import brave.Tracer;
import brave.Tracing;
import com.mysql.jdbc.*;
import zipkin.Endpoint;
import zipkin.TraceKeys;

import java.net.URI;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author dmz
 * @date 2017/7/7
 */
public class TracingStatementInterceptor implements StatementInterceptorV2 {

    /**
     * There's no attribute namespace shared across request and response. Hence, we need to save off
     * a reference to the span in scope, so that we can close it in the response.
     */
    final ThreadLocal<Tracer.SpanInScope> currentSpanInScope = new ThreadLocal<>();

    @Override
    public ResultSetInternalMethods preProcess(String sql, Statement interceptedStatement,
                                               Connection connection) throws SQLException {
        Tracer tracer = Tracing.currentTracer();
        if (tracer == null) return null;

        Span span = tracer.nextSpan();
        // regardless of noop or not, set it in scope so that custom contexts can see it (like slf4j)
        if (!span.isNoop()) {
            // When running a prepared statement, sql will be null and we must fetch the sql from the statement itself
            if (interceptedStatement instanceof PreparedStatement) {
                sql = ((PreparedStatement) interceptedStatement).getPreparedSql();
            }
            int spaceIndex = sql.indexOf(' '); // Allow span names of single-word statements like COMMIT
            span.kind(Span.Kind.CLIENT).name(spaceIndex == -1 ? sql : sql.substring(0, spaceIndex));
            span.tag(TraceKeys.SQL_QUERY, sql);
            parseServerAddress(connection, span);
            span.start();
        }

        currentSpanInScope.set(tracer.withSpanInScope(span));

        return null;
    }

    @Override
    public ResultSetInternalMethods postProcess(String sql, Statement interceptedStatement,
                                                ResultSetInternalMethods originalResultSet, Connection connection, int warningCount,
                                                boolean noIndexUsed, boolean noGoodIndexUsed, SQLException statementException)
            throws SQLException {
        Tracer tracer = Tracing.currentTracer();
        if (tracer == null) return null;

        Tracer.SpanInScope spanInScope = currentSpanInScope.get();
        if (spanInScope == null) return null;
        Span span = tracer.currentSpan();
        spanInScope.close();

        if (statementException != null) {
            span.tag(zipkin.Constants.ERROR, Integer.toString(statementException.getErrorCode()));
        }
        span.finish();

        return null;
    }

    /**
     * MySQL exposes the host connecting to, but not the port. This attempts to get the port from the
     * JDBC URL. Ex. 5555 from {@code jdbc:mysql://localhost:5555/database}, or 3306 if absent.
     */
    static void parseServerAddress(Connection connection, Span span) {
        try {
            URI url = URI.create(connection.getMetaData().getURL().substring(5)); // strip "jdbc:"
            int port = url.getPort() == -1 ? 3306 : url.getPort();
            String remoteServiceName = connection.getProperties().getProperty("zipkinServiceName");
            if (remoteServiceName == null || "".equals(remoteServiceName)) {
                String databaseName = connection.getCatalog();
                if (databaseName != null && !databaseName.isEmpty()) {
                    remoteServiceName = "mysql-" + databaseName;
                } else {
                    remoteServiceName = "mysql";
                }
            }
            Endpoint.Builder builder = Endpoint.builder().serviceName(remoteServiceName).port(port);
            if (!builder.parseIp(connection.getHost())) return;
            span.remoteEndpoint(builder.build());
        } catch (Exception e) {
            // remote address is optional
        }
    }

    @Override
    public boolean executeTopLevelOnly() {
        return true; // True means that we don't get notified about queries that other interceptors issue
    }

    @Override
    public void init(Connection conn, Properties props) throws SQLException {
        // Don't care
    }

    @Override
    public void destroy() {
        // Don't care
    }
}
