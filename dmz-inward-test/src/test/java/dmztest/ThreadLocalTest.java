package dmztest;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author dmz
 * @date 2016/12/30
 */
//class ThreadT extends Thread {
//
//    private ThreadLocalTest threadLocalTest;
//
//    public ThreadT(ThreadLocalTest threadLocalTest) {
//        this.threadLocalTest = threadLocalTest;
//    }
//
//    @Override
//    public void run() {
//        for (int i = 0; i < 3; i++) {
//            System.out.println(this + "--" + Thread.currentThread().getName() + "--" + threadLocalTest.addNum());
//        }
//    }
//}

public class ThreadLocalTest {

    private static AtomicReference<Thread> sign =new AtomicReference<>();

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {

        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    private static int a = 0;

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println(sign);

//        ExecutorService executor = Executors.newFixedThreadPool(2);
//        for (int i = 0; i < 10; i++) {
//            executor.execute(() -> {
//                a = threadLocal.get();
//                a += 1;
//                threadLocal.set(a);
//                System.out.println(Thread.currentThread().getName() + "==" + threadLocal.get());
//            });
//        }
//        executor.shutdown();
//    for (int i=0;i<10;i++) {
//            new Thread() {
//                @Override
//                public void run() {
//                    a = threadLocal.get();
//                    a += 1;
//                    threadLocal.set(a);
//                    try {
//                        Thread.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(Thread.currentThread().getName() + "==" + a);
//                }
//            }.start();
//        }
    }
}
