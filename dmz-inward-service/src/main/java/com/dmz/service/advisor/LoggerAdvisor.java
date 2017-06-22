package com.dmz.service.advisor;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by dmz on 2016/3/18.
 */
public class LoggerAdvisor implements MethodBeforeAdvice {
    private static Logger LOG = LoggerFactory.getLogger(LoggerAdvisor.class);

    public void before(Method method, Object[] args, Object target) throws Throwable {
        LOG.info("Class:{} - Method:{} - Args:{}",target.getClass().getName(),method.getName(), JSON.toJSONString(args));
    }
}
