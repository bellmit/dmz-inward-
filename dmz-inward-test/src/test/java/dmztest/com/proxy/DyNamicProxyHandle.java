package dmztest.com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by dmz on 2016/1/28.
 */
public class DyNamicProxyHandle {

//    private Object tar;

    //绑定委托对象，并返回代理类
    public Object bind(final Object tar)
    {
//        this.tar = tar;
        //绑定该类实现的所有接口，取得代理类
        return Proxy.newProxyInstance(tar.getClass().getClassLoader(),
                tar.getClass().getInterfaces(),
                new InvocationHandler(){
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("dmz---dmz");
                        return method.invoke(tar,args);
                    }
                });
    }
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        return method.invoke(tar,args);
//    }
}
