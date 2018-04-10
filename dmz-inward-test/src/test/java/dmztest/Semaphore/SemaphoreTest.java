package dmztest.Semaphore;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author dmz
 * @date 2017/11/22
 */
public class SemaphoreTest {

    private static final int COUNT = 40;
    private static CountDownLatch countDownLatch = new CountDownLatch(40);
    private static Executor executor = Executors.newFixedThreadPool(COUNT);
    private static Semaphore semaphore = new Semaphore(1);
    public static void main(String[] args) throws InterruptedException {
        for (int i=0; i< COUNT; i++) {
            executor.execute(new SemaphoreTest.Task());
        }
        countDownLatch.await();
        System.out.println("DOWN!");
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            try {
                //读取文件操作
                System.out.println("读文件:"+Thread.currentThread().getId());
                semaphore.acquire();
                // 存数据过程
                semaphore.release();
                System.out.println("释放成功："+Thread.currentThread().getId());
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
        }
    }
}
