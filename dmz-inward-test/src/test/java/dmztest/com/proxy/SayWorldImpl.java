package dmztest.com.proxy;

/**
 * Created by dmz on 2016/1/28.
 */
public class SayWorldImpl implements SayWorld {
    public void saySomething() {
        System.out.println("Test Dynamic Proxy!");
    }
}
