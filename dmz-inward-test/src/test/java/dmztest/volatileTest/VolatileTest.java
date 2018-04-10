package dmztest.volatileTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dmz
 * @date 2018/1/11
 */
public class VolatileTest {
    public static void main(String[] args) throws InterruptedException {

        Foo value = new Foo();
        CountDownLatch singleStart = new CountDownLatch(1);
        CountDownLatch threadDown = new CountDownLatch(1001);

        new Thread(() -> {
            try {
                singleStart.await();
                value.setX(10);
                threadDown.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                try {
                    singleStart.await();
                    System.out.println(value.getX());
                    threadDown.countDown();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        singleStart.countDown();
        threadDown.await();

    }
}

class ValueVisualSet {

    private AtomicInteger atomicInteger = new AtomicInteger();
    private volatile int anInt;

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
        atomicInteger.getAndIncrement();
    }
}


class Foo {
    private int x = -1;
    private volatile boolean v = false;
    public void setX(int x) {
        this.x = x;
        v = true;
    }
    public int getX() {
        if (v == true) {
            return x;
        }
        return 0;
    }
}
