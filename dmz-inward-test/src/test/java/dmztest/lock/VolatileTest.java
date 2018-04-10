package dmztest.lock;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author dmz
 * @date 2017/2/17
 */
class VolatileWhile {

    private volatile boolean spin = true;

    public void spinStart() {
        while (spin) {
            System.out.println("spin...");
        }
    }

    public void spinEnd() {
        endSpin();
    }

    private void endSpin() {
        boolean  a = false;
        spin = a;
    }
}

public class VolatileTest {
    public static void main(String[] args) throws InterruptedException, IOException {

        VolatileWhile vw = new VolatileWhile();
        CountDownLatch singleStart = new CountDownLatch(1);
        CountDownLatch threadStart = new CountDownLatch(11);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    singleStart.await();
                    vw.spinStart();
                    threadStart.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        new Thread(() -> {
            try {
                singleStart.await();
                vw.spinEnd();
                threadStart.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        singleStart.countDown();
        threadStart.await();

    }
}
