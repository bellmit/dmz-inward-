package dmztest.com.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by dmz on 2016/2/1.
 */
public class Proxy3 implements MethodInterceptor {
    private String name;
    public Proxy3(String name){
        this.name = name;
    }
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//        if (!"BOSS".equalsIgnoreCase(name)){
//            System.out.println("you have no permission!");
//            return null;
//        }
        System.out.println("START AOP...");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("AOP END...");
        return result;
    }
}
