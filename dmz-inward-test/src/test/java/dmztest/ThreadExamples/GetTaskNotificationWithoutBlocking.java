package dmztest.ThreadExamples;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author dmz
 * @date 2017/12/15
 */
public class GetTaskNotificationWithoutBlocking {

    public static void main(String... argv) throws Exception {
        ExampleService svc = new ExampleService();
        GetTaskNotificationWithoutBlocking listener = new GetTaskNotificationWithoutBlocking();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            CompletableFuture<String> f = CompletableFuture.supplyAsync(svc::work);
            f.thenAccept(listener::notify);
        }

        countDownLatch.await();
        System.out.println("Exiting main()");
    }

    void notify(String msg) {
        System.out.println("Received message: " + msg);
    }

}

class ExampleService {

    String work() {
        sleep(7000, TimeUnit.MILLISECONDS); /* Pretend to be busy... */
        char[] str = new char[5];
        ThreadLocalRandom current = ThreadLocalRandom.current();
        for (int idx = 0; idx < str.length; ++idx)
            str[idx] = (char) ('A' + current.nextInt(26));
        String msg = new String(str);
        System.out.println("Generated message: " + msg);
        return msg;
    }

    public static void sleep(long average, TimeUnit unit) {
        String name = Thread.currentThread().getName();
        long timeout = Math.min(exponential(average), Math.multiplyExact(10, average));
        System.out.printf("%s sleeping %d %s...%n", name, timeout, unit);
        try {
            unit.sleep(timeout);
            System.out.println(name + " awoke.");
        } catch (InterruptedException abort) {
            Thread.currentThread().interrupt();
            System.out.println(name + " interrupted.");
        }
    }

    public static long exponential(long avg) {
        return (long) (avg * -Math.log(1 - ThreadLocalRandom.current().nextDouble()));
    }

}
