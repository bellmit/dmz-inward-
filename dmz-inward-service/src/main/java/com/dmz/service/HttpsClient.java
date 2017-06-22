package com.dmz.service;


import com.dmz.service.adapters.ImprovedDateTypeAdapter;
import com.dmz.service.adapters.ImprovedEnumTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.*;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @author dmz
 * @tag
 * @memo 2016/6/3
 */
public abstract class HttpsClient extends SSLConnection {

    protected CloseableHttpClient httpClient;

    private IdleConnectionMonitorThread poolMonitor;

    /**
     * 最大连接数maxToal,
     * 每个路由基础的连接数maxPerRoute，(访问同一个主机则为一个路由)
     * 连接重试次数retryTime,
     * 请求连接超时时间requestTimeOut,從連接池中獲取鏈接的timeout
     * 建立连接超时时间connetTimeOut
     */
    protected int maxTotal = 200;
    protected int maxPerRoute = 2;
    protected int retryTime = 3;
    protected int inactivityTime = 1000; //空閑檢查時間
    protected int requestTimeout = 5000;
    protected int connectTimeout = 5000; // connect timed out threshold
    protected int socketTimeout = 5000; // read timed out threshold

    protected void initHttpsClient() {

        initSSLConnection();

        PoolingHttpClientConnectionManager poolClientManage = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        poolClientManage.setMaxTotal(maxTotal);
        poolClientManage.setDefaultMaxPerRoute(maxPerRoute);
        // 空闲连接检查时间
        poolClientManage.setValidateAfterInactivity(inactivityTime);
//        HttpHost httpHost = new HttpHost(hostname, port);
//        // 将目标主机的最大连接数增加
//        poolClientManage.setMaxPerRoute(new HttpRoute(httpHost), maxRoute);

        HttpRequestRetryHandler requestRetry = (exception, executionCount, context) -> {
            if (executionCount >= retryTime) {
                return false;
            }
            if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                return true;
            }
            if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                return false;
            }
            if (exception instanceof InterruptedIOException) {// 超时
                return false;
            }
            if (exception instanceof UnknownHostException) {// 目标服务器不可达
                return false;
            }
            if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
                return false;
            }
            if (exception instanceof SSLException) {// SSL握手异常
                return false;
            }

            HttpClientContext clientContext = HttpClientContext
                    .adapt(context);
            HttpRequest request = clientContext.getRequest();
            // 如果请求是幂等的，就再次尝试
            if (!(request instanceof HttpEntityEnclosingRequest)) {
                return true;
            }
            return false;
        };

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(requestTimeout).build();
        SocketConfig socketConfig = SocketConfig.custom()
                .setTcpNoDelay(true)
                .setSoKeepAlive(false)
                .setSoTimeout(socketTimeout).build();

//        KeepAlive策略
        ConnectionKeepAliveStrategy keepAliveStrate = new DefaultConnectionKeepAliveStrategy() {

            @Override
            public long getKeepAliveDuration(
                    HttpResponse response,
                    HttpContext context) {
                long keepAlive = super.getKeepAliveDuration(response, context);
                if (keepAlive == -1) {
                    // Keep connections alive 5 seconds if a keep-alive value
                    // has not be explicitly set by the server
                    keepAlive = 5000;
                }
                return keepAlive;
            }
        };

        httpClient = HttpClients.custom()
                .setConnectionManager(poolClientManage)
                .setDefaultSocketConfig(socketConfig)
                .setDefaultRequestConfig(requestConfig)
                .setKeepAliveStrategy(keepAliveStrate)
                .setRetryHandler(requestRetry).build();

        startMonitor(poolClientManage);
    }

    private void startMonitor(HttpClientConnectionManager poolClientManage) {
        this.poolMonitor = new IdleConnectionMonitorThread(poolClientManage);
        poolMonitor.start();
    }

    protected void shutdown() {
        poolMonitor.shutdown();
    }

    /**
     * 线程回收策略
     */
    private class IdleConnectionMonitorThread extends Thread {

        private final HttpClientConnectionManager connMgr;
        private volatile boolean shutdown;

        public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr) {
            super();
            this.connMgr = connMgr;
        }

        @Override
        public void run() {
            try {
                while (!shutdown) {
                    synchronized (this) {
                        wait(5000);
                        // 关闭失效的连接
                        connMgr.closeExpiredConnections();
                        // 可选的, 关闭30秒内不活动的连接
                        connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
                    }
                }
            } catch (InterruptedException ex) {
                // terminate
            }
        }

        public void shutdown() {
            shutdown = true;
            synchronized (this) {
                notifyAll();
            }
        }

    }

    protected String post(String host, String path, Map<String, String> params) {

        URIBuilder builder = new URIBuilder().setScheme("https")
                .setHost(host)
                .setPath(path);
        for (String key : params.keySet()) {
            builder.setParameter(key, params.get(key).toString());
        }

        try {

            URI uri = builder.build();
            RequestBuilder requestBuilder = RequestBuilder.post(uri);
            List<NameValuePair> kvs = new ArrayList<>();
            for (String key : params.keySet()) {
                kvs.add(new BasicNameValuePair(key, params.get(key)));
            }
            requestBuilder.setEntity(new UrlEncodedFormEntity(kvs, "UTF-8"));
            HttpUriRequest request = requestBuilder.build();

            CloseableHttpResponse resp = httpClient.execute(request);
            if (resp.getStatusLine().getStatusCode() >= 300) {
                System.err.println("Something wrong: " + resp.getStatusLine().toString());
                return resp.getStatusLine().toString();
            }

            BufferedReader input = new BufferedReader(new InputStreamReader(resp.getEntity().getContent(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            char[] buf = new char[1000];
            int count;
            while ((count = input.read(buf)) > 0) {
                sb.append(buf, 0, count);
            }
            return sb.toString();

        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    protected <T> T get(String host, String path, Map<String, String> params, Type type) {

        URIBuilder builder = new URIBuilder().setScheme("https")
                .setHost(host)
                .setPath(path);
        builder.setCharset(Charset.forName("UTF-8"));//设置URLEncode 为UTF-8
        for (String key : params.keySet()) {
            builder.setParameter(key, params.get(key).toString());
        }
//        连接的释放 资源的释放不再需要手动
        ResponseHandler<T> rh;
        rh = response -> {
            StatusLine statusLine = response.getStatusLine();
            HttpEntity entity = response.getEntity();
            if (statusLine.getStatusCode() >= 300) {
                throw new HttpResponseException(
                        statusLine.getStatusCode(),
                        statusLine.getReasonPhrase());
            }
            if (entity == null) {
                throw new ClientProtocolException("Response contains no content");
            }
            Reader reader = new InputStreamReader(entity.getContent(), "UTF-8");

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new ImprovedDateTypeAdapter());
//            gsonBuilder.registerTypeAdapter(RiskLevel.class, new ImprovedEnumType());
//            gsonBuilder.registerTypeAdapter(RiskLevel.class, new ImprovedEnumTypeAdapter());
            gsonBuilder.registerTypeAdapterFactory(ImprovedEnumTypeAdapter.FACTORY);
            Gson gson = gsonBuilder.create();
            return gson.fromJson(reader, type);
        };
        try {
            URI uri = builder.build();
            HttpGet httpGet = new HttpGet(uri);
//            httpGet.setHeader("Connection", "close"); // 短链接
            T resp = httpClient.execute(httpGet, rh);
            return resp;
        } catch (HttpResponseException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    protected  <T> T get(String host, String path, Map<String, String> params ,Type type){
//
//        URIBuilder builder = new URIBuilder().setScheme("https")
//                .setHost(host)
//                .setPath(path);
//
//        for (String key : params.keySet()) {
//            builder.setParameter(key, params.get(key).toString());
//        }
////        连接的释放 资源的释放不再需要手动
//        ResponseHandler<T> rh = response -> {
//            StatusLine statusLine = response.getStatusLine();
//            HttpEntity entity = response.getEntity();
//            if (statusLine.getStatusCode() >= 300) {
//                throw new HttpResponseException(
//                        statusLine.getStatusCode(),
//                        statusLine.getReasonPhrase());
//            }
//            if (entity == null) {
//                throw new ClientProtocolException("Response contains no content");
//            }
//            Reader reader = new InputStreamReader(entity.getContent(), "UTF-8");
//            Gson gson = new GsonBuilder().create();
//            return gson.fromJson(reader, type);
//        };
//        try {
//            URI uri = builder.build();
//            HttpGet httpGet = new HttpGet(uri);
//            T resp = httpClient.execute(httpGet, rh);
//            return resp;
//        } catch (HttpResponseException e) {
//            e.printStackTrace();
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    protected String get(String host, String path, Map<String, String> params) {
//
//        URIBuilder builder = new URIBuilder().setScheme("https")
//                .setHost(host)
//                .setPath(path);
//
//        for (String key : params.keySet()) {
//            builder.setParameter(key, params.get(key).toString());
//        }
//        CloseableHttpResponse resp = null;
//        try {
//            URI uri = builder.build();
//            HttpGet httpGet = new HttpGet(uri);
//            resp = httpClient.execute(httpGet);
//            if (resp.getStatusLine().getStatusCode() >= 300) {
//                System.err.println("Something wrong: " + resp.getStatusLine().toString());
//            }
//            HttpEntity entity = resp.getEntity();
//            if (entity != null) {
//                entity = new BufferedHttpEntity(entity);
//            }
//            BufferedReader input = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
//            StringBuilder sb = new StringBuilder();
//            char[] buf = new char[1000];
//            int count;
//            while ((count = input.read(buf)) > 0) {
//                sb.append(buf, 0, count);
//            }
//            return sb.toString();
//
//        } catch (IOException | URISyntaxException e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                if (resp != null)
//                    resp.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxPerRoute() {
        return maxPerRoute;
    }

    public void setMaxPerRoute(int maxPerRoute) {
        this.maxPerRoute = maxPerRoute;
    }

    public int getRetryTime() {
        return retryTime;
    }

    public void setRetryTime(int retryTime) {
        this.retryTime = retryTime;
    }
}
