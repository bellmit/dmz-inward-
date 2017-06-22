package dmztest.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dmz
 * @date 2017/3/22
 */
public class LockReentrantExample {
    private static volatile int anInt;
    private static Lock lock = new ReentrantLock(true);

    private static void increment() {
        lock.lock();
        anInt++;
        lock.unlock();
    }
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5000; i++) {
            Thread t = new Thread(() -> {
//                lock.lock();
                increment();
//                lock.unlock();
            });
            t.start();
            t.join();
        }

        System.out.println(anInt);

    }
}
