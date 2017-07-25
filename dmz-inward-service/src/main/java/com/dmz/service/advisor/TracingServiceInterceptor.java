package com.dmz.service.advisor;

import brave.Span;
import brave.Tracing;
import com.dmz.service.ProxyTargetUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;

/**
 * @author dmz
 * @date 2017/7/7
 */
public class TracingServiceInterceptor implements MethodInterceptor {

    private static Logger logger = LoggerFactory.getLogger(TracingServiceInterceptor.class);

    @Resource(name = "tracing")
    private Tracing tracing;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        String className = ProxyTargetUtil.getTarget(methodInvocation.getThis()).getClass().getName();
        String methodDesc = className + "." + methodInvocation.getMethod().getName();

        Span span = null;
        try {
            span = tracing.tracer().nextSpan().name(methodDesc).start();
        } catch (Exception e) {
            logger.error("TRACING MEET ERROR:{}",e);
        }

        Class<?> result = methodInvocation.getMethod().getReturnType();
        try {
            return methodInvocation.proceed();
        } catch (Throwable throwable) {
            logger.error("Inner Service Error:{}",throwable);
            if (throwable instanceof ConstraintViolationException) {
                throw throwable;
            }
            if (result.isAssignableFrom(Void.class)) {
                return Void.class;
            }
            Object object = result.newInstance();
            return object;
        } finally {
            try {
                span.finish();
            } catch (Exception e) {
                logger.error("TRACING MEET ERROR:{}",e);
            }
        }
    }
}
