package dmztest.com.jdkproxy;

/**
 * Created by dmz on 2016/1/28.
 */
public class DynamicCount extends Test implements Count {
    public void update() {
        System.out.println("updating DynamicCount...");
        update2();
    }

    public void update2() {
        System.out.println("updating DynamicCount 2...");
    }
}
