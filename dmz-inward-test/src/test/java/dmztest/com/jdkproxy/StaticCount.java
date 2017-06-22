package dmztest.com.jdkproxy;

/**
 * Created by dmz on 2016/1/28.
 */
public class StaticCount implements Count {
    public void update() {
        System.out.println("updating staticCount...");
    }
}
