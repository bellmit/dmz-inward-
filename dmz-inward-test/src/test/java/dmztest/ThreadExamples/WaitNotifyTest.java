package dmztest.ThreadExamples;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmz on 2016/8/4.
 */

public class WaitNotifyTest implements Serializable {
    //    private static final long serialVersionUID = -769499962328419087L;
    //    private static final long serialVersionUID = -769499962328419087L;
    private List<Integer> queue = new ArrayList<>();
    private int CAPACITY = 5;

    public static void main(String[] args) {
        WaitNotifyTest waitNotify = new WaitNotifyTest();
        new Thread(waitNotify.new Produce()).start();
        new Thread(waitNotify.new Consumer()).start();
    }

    class Produce implements Runnable {
        int count;

        @Override
        public void run() {
            while (true) {
                try {
                    produce(count++);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        private void produce(int count) throws InterruptedException {

            synchronized (queue) {
                if (queue.size() == CAPACITY) {
                    System.out.println("Queue is Full...");
                    queue.wait();
                    return;
                }
                Thread.sleep(1000);
                queue.add(count);
                System.out.println("Produced :" + count);
                queue.notify();
            }
        }
    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    consum();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void consum() throws InterruptedException {
            synchronized (queue) {
                if (queue.isEmpty()) {
                    System.out.println("Queue is Empty...");
                    queue.wait();
                    return;
                }

                Thread.sleep(1000);
                int i = queue.remove(0);
                System.out.println("Consumed :" + i);
                queue.notify();
            }
        }
    }
}
