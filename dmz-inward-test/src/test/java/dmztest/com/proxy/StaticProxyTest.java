package dmztest.com.proxy;

/**
 * Created by dmz on 2016/1/28.
 */
interface Say{
    void saySomething();
}
class SayImpl implements Say{

    public void saySomething() {
        System.out.println("HELLO WORLD!");
    }
}
class SayImplProxy implements Say{
    private Say s = new SayImpl();
    public void saySomething() {
        System.out.println("PROXY!");
        s.saySomething();
    }
}
public class StaticProxyTest {
    public static void main(String[] args){
        Say s = new SayImplProxy();
        s.saySomething();
    }
}
