package dmztest.com.beans;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by dmz on 2016/2/15.
 */
public class WorkAspect implements MethodBeforeAdvice, AfterReturningAdvice {
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("工作结束回家。。。");
    }

    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("去公司准备工作。。。");
    }
}
