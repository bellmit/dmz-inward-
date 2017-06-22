package dmztest.com.jdkproxy;

/**
 * Created by dmz on 2016/1/28.
 */
public class StaticCountProxyTest {
    public static void main(String[] args){
        Count proxy = new StaticCountProxy(new StaticCount());
        proxy.update();
    }
}
