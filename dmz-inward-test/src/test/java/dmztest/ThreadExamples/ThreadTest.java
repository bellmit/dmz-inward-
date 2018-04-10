package dmztest.ThreadExamples;

import java.io.IOException;

/**
 * @author dmz
 * @date 2018/4/3
 */
public class ThreadTest {

    private static Thread gloable;
    public static void main(String[] args) throws IOException {
        gloable = new Thread(() -> {
            long start = System.currentTimeMillis();
            for (; ; ) {
                //if (System.currentTimeMillis() - start > 500000) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Test Null Thread");
                //}
            }
        });

        Thread nullThread = new Thread(() -> {
            gloable = null;
        });

        gloable.start();
        nullThread.start();

        System.in.read();
    }
}
