//package com.dmz.service.advisor;
//
//import brave.Span;
//import brave.Tracing;
//import com.dmz.service.ProxyTargetUtil;
//import org.apache.ibatis.executor.statement.StatementHandler;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.plugin.Intercepts;
//import org.apache.ibatis.plugin.Invocation;
//import org.apache.ibatis.plugin.Signature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.annotation.Resource;
//import java.sql.Connection;
//import java.util.Properties;
//
///**
// * @author dmz
// * @date 2017/7/7
// */
//@Intercepts({@Signature(
//        type = StatementHandler.class,
//        method = "prepare",
//        args = {Connection.class}
//)})
//public class TracingMybatisInterceptor implements Interceptor {
//
//    private static Logger logger = LoggerFactory.getLogger(TracingMybatisInterceptor.class);
//
//    @Resource(name = "tracing")
//    private Tracing tracing;
//
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//        String className = ProxyTargetUtil.getTarget(invocation.getTarget()).getClass().getName();
//        String methodDesc = className + "." + invocation.getMethod().getName();
//        Span span = tracing.tracer().newChild(tracing.tracer().currentSpan().context()).name(methodDesc).start();
//        Object object = null;
//        try {
//            object = invocation.proceed();
//        } catch (Exception e) {
//            logger.error("MyBatis Mapper Error : {}",e);
//        } finally {
//            span.finish();
//        }
//        return object;
//    }
//
//    @Override
//    public Object plugin(Object o) {
//        return null;
//    }
//
//    @Override
//    public void setProperties(Properties properties) {
//
//    }
//}
