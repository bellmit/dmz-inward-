package dmztest.com.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by dmz on 2016/1/28.
 */
public class DynamicCountProxy implements InvocationHandler {
    private Object target;
    public Object bind(Object object){
        this.target = object;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start dynamic proxy...");
        Object result = method.invoke(target,args);
        System.out.println("end dynamic proxy...");
        return result;
    }
}
