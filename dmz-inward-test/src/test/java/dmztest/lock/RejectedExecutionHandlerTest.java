package dmztest.lock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author dmz
 * @date 2017/2/10
 */
public class RejectedExecutionHandlerTest {
    private static final int THREAD_SIZE = 1;
    private static final int CAPACITY = 1;
    public static void main(String[] args) {

        ThreadPoolExecutor pool = new ThreadPoolExecutor(THREAD_SIZE, THREAD_SIZE, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(CAPACITY));
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            pool.execute(() -> System.out.println("Task-" + finalI));
        }

    }
}
