package com.dmz.test.bean;



import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;

/**
 * Created by dmz on 2016/12/2.
 */
public class LogFactoryBean implements InitializingBean, FactoryBean<Object> {

    private String interfaceName;

    private Object targetClass;

    private Object proxyClass;

    @Override
    public Object getObject() throws Exception {
        return proxyClass;
    }

    @Override
    public Class<?> getObjectType() {
        return proxyClass==null? Object.class:proxyClass.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public Object getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Object targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        proxyClass = Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[] { Class.forName(interfaceName) }, new InvocationHandler() {

                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        System.out.println("method:" + method.getName());
                        System.out.println("Method before...");
                        Object result = method.invoke(targetClass, args);
                        System.out.println("Method after...");
                        return result;
                    }
                });
    }
}
