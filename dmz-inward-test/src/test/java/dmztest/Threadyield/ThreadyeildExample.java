package dmztest.Threadyield;

/**
 * Created by dmz on 2016/8/22.
 */
class Consume implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("I am Consume " + i);
            Thread.yield();
        }
    }
}

class Produce implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("I am Produce " + i);
            Thread.yield();
        }
    }
}

public class ThreadyeildExample {
    public static void main(String arg[]) throws InterruptedException {

        Thread consume = new Thread(new Consume());
        Thread produce = new Thread(new Produce());
        consume.setPriority(Thread.MAX_PRIORITY);
        produce.setPriority(Thread.MAX_PRIORITY);
        produce.start();
        consume.start();
//        Thread t = new Thread(() -> {
//            System.out.println("First task started");
//            System.out.println("Sleeping for 2 seconds");
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("First task completed");
//        });
//        Thread t2 = new Thread(() -> System.out.println("Second task completed"));
//        t.start();
//        t.join();
////        t2.start();
//        System.out.println("Main Thread End.");

    }
}
