package dmztest.lock;

/**
 * @author dmz
 * @date 2017/2/7
 */
public class CLHLockStart {
    public static void main(String[] args) {
        final CLHLock clhLock = new CLHLock();
        for (int i=0;i<100;i++) {
            new Thread(()-> {
                try {
                    clhLock.lock();
                    System.out.println(Thread.currentThread().getId()+" acquired lock.");
                    clhLock.unLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
