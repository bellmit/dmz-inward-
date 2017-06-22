package dmztest.ThreadPoolExample;

import java.util.concurrent.*;

/**
 * Created by dmz on 2016/8/16.
 */
class ThreadDemo implements Runnable {

    public String getName() {
        return name;
    }

    private String name;

    public ThreadDemo(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        }  catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Executing:" + name + ", Thread Name:"+Thread.currentThread().getName());
    }
}
public class ThreadPoolExample {

    public static void main(String[] args) {
        Integer threadCounter = 0;
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(5);
        ThreadPoolExecutor execute = new ThreadPoolExecutor(5, 20, 5000, TimeUnit.MILLISECONDS, blockingQueue);
        execute.setRejectedExecutionHandler((r, executor) -> {
            System.out.println("Task Rejected : "
                    + ((ThreadDemo) r).getName());
            System.out.println("Waiting for a second !!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Lets add another time : "
                    + ((ThreadDemo) r).getName());
            executor.execute(r);
        });
        int number = execute.prestartAllCoreThreads();
//        System.out.println(number);

        while (true) {
            threadCounter++;
            // Adding threads one by one
            System.out.println("Adding ThreadDemo : " + threadCounter);
            execute.execute(new ThreadDemo(threadCounter.toString()));

            if (threadCounter == 100)
                break;
        }
        execute.shutdown();
    }

}
