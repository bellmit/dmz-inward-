package com.dmz.service.advisor;

import com.dmz.service.exceptions.BasicException;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dmz on 2016/8/19.
 */
public class LoggerExceptionAdvice implements MethodInterceptor {

    private static Logger LOG = LoggerFactory.getLogger(LoggerExceptionAdvice.class);

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        Object result = methodInvocation.getMethod().getReturnType().newInstance();
        try {
            return methodInvocation.proceed();
        } catch (BasicException e) {
            LOG.error("[数据库异常]  Message:{} ,RequestContent:{}, Throwable:{}", e.getMessage(), e.getJsonContext(), e.getEx());
            return result;
        }
    }
}
