package dmztest;

/**
 * Created by dmz on 2016/8/22.
 */
public class MemeryExample {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().totalMemory()/1024);
        System.out.println(Runtime.getRuntime().freeMemory()/1024);
    }
}
