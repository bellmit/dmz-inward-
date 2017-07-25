package com.dmz.service;

import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Field;

/**
 * @author dmz
 * @date 2017/6/29
 */
public class ProxyTargetUtil {

    public static Object getTarget(Object proxy) throws Exception {

        if (!AopUtils.isAopProxy(proxy)) {
            return proxy;
        }
        if (AopUtils.isJdkDynamicProxy(proxy)) {
            return getJdkDynamicProxyTarget(proxy);
        }

        if (AopUtils.isCglibProxy(proxy)) {
            return getCglibProxyTarget(proxy);
        }

        return null;
    }

    public static String getTargetClassName(Object proxy) throws Exception {
        return getTarget(proxy).getClass().getName();
    }

    private static Object getJdkDynamicProxyTarget(Object proxy) throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy = (AopProxy) h.get(proxy);
        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        Object target = ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
        return target;
    }

    private static Object getCglibProxyTarget(Object proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);
        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        Object target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
        return target;
    }

    private static Object getTargetClass(Object proxy) {
        Class<?> target = AopProxyUtils.ultimateTargetClass(proxy);
        return target;
    }
}
