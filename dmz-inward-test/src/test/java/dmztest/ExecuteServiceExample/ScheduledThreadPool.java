package dmztest.ExecuteServiceExample;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by dmz on 2016/8/23.
 */
class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            System.out.println("Doing a task during : " + name + " - Time - " + new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class ScheduledThreadPool {
    public static void main(String[] args) throws InterruptedException {
//                delayExecuteThread();
                periodicallyThread();


    }

    private static void periodicallyThread() throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        Task task1 = new Task("Demo Task 1");
        Task task2 = new Task("Demo Task 2");
        System.out.println("The time is " + new Date());
        executor.scheduleAtFixedRate(task1, 2, 10, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(task2, 0, 10, TimeUnit.SECONDS);

        ThreadPoolExecutor ex = (ThreadPoolExecutor) executor;
        System.out.println("Active Count " + ex.getActiveCount());
        System.out.println("Current Pool Size " + ex.getPoolSize());
        System.out.println("Core Pool Size "+ex.getCorePoolSize());
        System.out.println("Completed Count " + ex.getCompletedTaskCount());
        System.out.println("Largest Pool Size "+ex.getLargestPoolSize());
        System.out.println("Max Pool Size " + ex.getMaximumPoolSize());
        System.out.println("Max Integer Value "+Integer.MAX_VALUE);

        TimeUnit.SECONDS.sleep(11);
//        TimeUnit.SECONDS.sleep(10);
        executor.shutdown();
    }

    private static void delayExecuteThread() throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        Task task1 = new Task("Demo Task 1");
        Task task2 = new Task("Demo Task 2");
        System.out.println("The time is : " + new Date());
        executor.schedule(task1, 5, TimeUnit.SECONDS);
        executor.schedule(task2, 10, TimeUnit.SECONDS);
        executor.shutdown();
//        executor.shutdownNow();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
