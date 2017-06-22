package dmztest.ExecuteServiceExample;

import java.util.concurrent.*;

/**
 * Created by dmz on 2016/8/23.
 */
class Procuder implements Runnable {

    private String name;

    public Procuder(int name) {
        this.name = name+"";
    }
    @Override
    public void run() {
        try {
            System.out.println("Executing Procuder....ThreadName:"+Thread.currentThread().getName()+",ThreadGroup:"+Thread.currentThread().getThreadGroup());
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class FixedThreadPool {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadPoolExecutor pool = (ThreadPoolExecutor)Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            System.out.println("Start "+i+"  Procuder...");
            Future fulture = pool.submit(new Procuder(i));
//            System.out.println("isCancelled "+fulture.isCancelled());
//            System.out.println("isDone "+fulture.isDone());
//            System.out.println(fulture.get());
//            pool.execute(new Procuder(i));
        }
        System.out.println("Max Thread Pool "+pool.getMaximumPoolSize());
        System.out.println(pool.isTerminated());
        System.out.println(pool.isShutdown());
        pool.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);

        pool.shutdown();
        System.out.println("Main Thread Stop!");
        System.out.println(pool.isTerminated());
        System.out.println(pool.isShutdown());
    }
}
