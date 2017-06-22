package dmztest.QueueExamples;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dmz on 2016/8/16.
 */
class Consumer implements Runnable {

    private volatile boolean isStart = true;

    private BlockingQueue<String> blockingQueue;

    public Consumer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            while (isStart) {
                System.out.println("开始消费数据");
                String data = blockingQueue.poll(2, TimeUnit.SECONDS);
                if (data != null) {
                    System.out.println("拿到数据:" + data);
                    System.out.println("消费数据中...");
                    Thread.sleep(5 * 1000);
                } else {
                    isStart = false;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("消费者线程停止...");
        }
    }
}

class Producer implements Runnable {

    private BlockingQueue<String> blockingQueue;

    private static AtomicInteger atomicInteger = new AtomicInteger();

    private volatile boolean isStart = true;

    public Producer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        String data = null;
        try {
            while (isStart) {
                System.out.println("正在产生数据...");
                data = "data:" + atomicInteger.incrementAndGet();
                System.out.println("将数据放入队列:" + data);
                boolean isSuccess = blockingQueue.offer(data, 2, TimeUnit.SECONDS);

                if (!isSuccess) {
                    System.out.println("放入数据失败");
                }
                Thread.sleep(1000 * 10);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("退出生产者");
        }
    }

    public void stop() {
        isStart = false;
    }
}

public class QueueTest {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);
        Producer producer1 = new Producer(blockingQueue);
        Producer producer2 = new Producer(blockingQueue);
        Producer producer3 = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(producer1);
        executor.execute(producer2);
        executor.execute(producer3);
        executor.execute(consumer);

        Thread.sleep(10 * 1000);
        producer1.stop();
        producer2.stop();
        producer3.stop();

        executor.shutdown();

        Thread.sleep(10);

        TimeUnit.SECONDS.sleep(10);
    }
}
