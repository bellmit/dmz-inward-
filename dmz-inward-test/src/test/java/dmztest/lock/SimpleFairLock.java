package dmztest.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dmz
 * @date 2017/3/22
 */
public class SimpleFairLock {

    private AtomicInteger tickTime = new AtomicInteger();
    private AtomicInteger serverNum = new AtomicInteger();
    private ThreadLocal<Integer> local = new ThreadLocal<>();

    public SimpleFairLock() {

    }

    public void lock() throws InterruptedException {
        int tick = tickTime.getAndIncrement();
        local.set(tick);
        while (tick != serverNum.get()) {
            System.out.println("waiting...");
            TimeUnit.SECONDS.sleep(1);

        }
    }

    public void unLock() {
        Integer server = local.get();
        serverNum.compareAndSet(server, server + 1);
    }
}
