package dmztest.com.jdkproxy;

import com.sun.tools.javac.Main;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * @author dmz
 * @date 2018/1/24
 */
public class ProxyCreateClassInstance {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {


        DynamicCount dynamicCount = new DynamicCount();

        //源码编译
        Main.compile(new String[]{"-d", ".", "sourceName"});



        // 创建代理class，被缓存在concurrentMap中
        Class clazz = Proxy.getProxyClass(ProxyCreateClassInstance.class.getClassLoader(), new Class[]{Count.class});
        //ProxyGenerator.generateProxyClass()


        // 获取构造函数
        Constructor constructor = clazz.getConstructor(new Class[]{InvocationHandler.class});


        // 创建代理类实例
        Count proxyInstance = (Count) constructor.newInstance(new Object[]{(InvocationHandler) (proxy, method, args1) -> {
            System.out.println("start proxy...");
            Object result = method.invoke(dynamicCount, args);
            System.out.println("end proxy...");
            return result;
        }});


        proxyInstance.update();

    }
}
