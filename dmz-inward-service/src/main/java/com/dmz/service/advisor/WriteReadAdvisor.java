package com.dmz.service.advisor;

import com.dmz.basic.DataReadBalance;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author dmz
 * @date 2017/1/9
 */
public class WriteReadAdvisor implements MethodInterceptor {

    @Resource
    private DataReadBalance dataReadBalance;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        Method method = methodInvocation.getMethod();
        Transactional annotation = method.getAnnotation(Transactional.class);

        if (annotation == null) {
            annotation = methodInvocation.getThis().getClass().getMethod(method.getName(), method.getParameterTypes()).getAnnotation(Transactional.class);
        }
        if (annotation != null && annotation.readOnly()) {
            dataReadBalance.setDataSourceKey();
        }

        Object object = null;
        try {
            object = methodInvocation.proceed();
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            dataReadBalance.removeDataSourceKey();
        }

        return object;
    }
}
