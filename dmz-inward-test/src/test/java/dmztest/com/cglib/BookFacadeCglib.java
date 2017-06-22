package dmztest.com.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by dmz on 2016/1/28.
 */
public class BookFacadeCglib implements MethodInterceptor {
    private Object target;
    /**
     * 创建代理对象
     *
     * @param target
     * @return
     */
    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if (method.getName().startsWith("add")){
            System.out.println("start transcation...");
            Object result = methodProxy.invokeSuper(obj, args);
            System.out.println("end transcation...");
            return result;
        }
        return null;
    }
}
