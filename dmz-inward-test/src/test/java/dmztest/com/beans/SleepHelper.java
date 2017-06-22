package dmztest.com.beans;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by dmz on 2016/2/15.
 */
public class SleepHelper implements MethodBeforeAdvice, AfterReturningAdvice {
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("睡醒之后穿衣服");
    }

    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("睡觉之前脱衣服");
    }
}
