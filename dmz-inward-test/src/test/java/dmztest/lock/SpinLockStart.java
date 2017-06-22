package dmztest.lock;

/**
 * @author dmz
 * @date 2017/2/6
 */
public class SpinLockStart {

    public static void main(String[] args) throws InterruptedException {

        final SpinLock spinLock = new SpinLock();

        for (int i=0;i<100;i++) {
            new Thread(() -> {
                try {
                    spinLock.lock();
                    System.out.println(Thread.currentThread().getId()+" acquired the lock!");
                    spinLock.unLock();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }
}
