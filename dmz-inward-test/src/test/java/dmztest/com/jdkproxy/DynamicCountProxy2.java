package dmztest.com.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by dmz on 2016/1/28.
 */
public class DynamicCountProxy2{
    public Object bind(final Object target){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("start transcation...");
                Object result = method.invoke(target,args);
                System.out.println("end transcation...");
                return result;
            }
        });
    }

    public Object proxy(final Object target){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),(Object proxy, Method method, Object[] args)->{
            System.out.println("start transcation...");
            Object result = method.invoke(target,args);
            System.out.println("end transcation...");
            return result;
        });
    }
}
