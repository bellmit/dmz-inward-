package dmztest.com.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by dmz on 2016/2/1.
 */
public class SaySomethingCglibProxy2 {
    private Object object;
    private String name;
    public SaySomethingCglibProxy2(String name){
        this.name = name;
    }
    public Object getProxySaything(Object object){
        this.object = object;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.object.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.print(name);
                Object result = methodProxy.invokeSuper(o,objects);
                return result;
            }
        });
        return enhancer.create();
    }
}
