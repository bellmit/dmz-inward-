package dmztest.com.cglib;

/**
 * Created by dmz on 2016/2/1.
 */
public class SaySomethingCglibProxyTest {
    public static void main(String[] args){
//        SaySomethingCglibProxy proxySaything = new SaySomethingCglibProxy("BOSS");
//        SaySomething proxySaythings = (SaySomething) proxySaything.SaySomethingCglibProxy(new SaySomething());
//        SaySomethingCglibProxy2 proxySaything = new SaySomethingCglibProxy2("BOSS");
//        SaySomething proxySaythings = (SaySomething) proxySaything.getProxySaything(new SaySomething());
//        proxySaythings.saytoSomeBody();
        SaySomethingCglibProxy3 proxy = new SaySomethingCglibProxy3(new QueryUpdateTest());
        QueryUpdateTest temproxy = (QueryUpdateTest)proxy.proxyCglib(new CallbackFilter3(), new Proxy3("BOSS"));
        temproxy.update();
    }
}
