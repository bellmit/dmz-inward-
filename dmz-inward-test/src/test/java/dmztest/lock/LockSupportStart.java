package dmztest.lock;

/**
 * @author dmz
 * @date 2017/2/7
 */
//綫程的阻塞與喚醒
public class LockSupportStart {
    private static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                synchronized (object) {
                    System.out.println("before interrupted:" + Thread.currentThread().isInterrupted());
//                Thread.sleep(10000);
//                LockSupport.park();
                    object.wait();
//                System.out.println("after interrupted:" + Thread.currentThread().isInterrupted());
                }
                System.out.println(Thread.currentThread().getId() + " awake!");
                System.out.println("after interrupted:" + Thread.currentThread().isInterrupted());
            } catch (Exception e) {
                System.out.println("after error interrupted:" + Thread.currentThread().isInterrupted());
                e.printStackTrace();
            }
        });
        t.start();
        Thread.sleep(1000);
        t.interrupt();

    }
}
