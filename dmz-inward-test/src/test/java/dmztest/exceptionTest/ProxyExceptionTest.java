package dmztest.exceptionTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.net.ConnectException;

/**
 * @author dmz
 * @date 2018/1/25
 */
public class ProxyExceptionTest {
    public static void main(String[] args) throws ConnectException {

        AThrowAImpl aThrowA = new AThrowAImpl();

        InvocationHandler h = (proxy, method, args1) -> {
            Object object = null;
            try {
                System.out.println("HH Start");
                method.invoke(aThrowA, args1);
            }

            catch (InvocationTargetException e) {

                e.printStackTrace();
                System.err.println("invocationTargetException " +e.getCause());
            } catch (IllegalAccessException e) {

                e.printStackTrace();
            }

            return object;
        };

        AThrowA proxyClassInstance  = (AThrowA) Proxy.newProxyInstance(ProxyExceptionTest.class.getClassLoader(), new Class[]{AThrowA.class}, h);

        proxyClassInstance.a();
    }
}
