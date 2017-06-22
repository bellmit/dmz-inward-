package dmztest.com.proxy;

/**
 * Created by dmz on 2016/1/28.
 */
public class DyNamicProxyTest {
    public static void main(String[] args) {
        DyNamicProxyHandle proxy = new DyNamicProxyHandle();
        //绑定该类实现的所有接口
        SayWorld sayWorld = (SayWorld) proxy.bind(new SayWorldImpl());
        sayWorld.saySomething();
    }
}
