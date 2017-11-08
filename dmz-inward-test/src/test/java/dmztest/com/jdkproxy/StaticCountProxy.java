package dmztest.com.jdkproxy;

/**
 * Created by dmz on 2016/1/28.
 */
public class StaticCountProxy implements Count {
    private StaticCount count;
    public StaticCountProxy(StaticCount count){
        this.count = count;
    }
    public void update() {
        System.out.println("Start Transcation....");
        count.update();
        System.out.println("End Transcation...");

    }

    @Override
    public void update2() {

    }
}
