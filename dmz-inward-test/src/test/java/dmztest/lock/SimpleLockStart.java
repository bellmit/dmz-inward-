package dmztest.lock;

/**
 * @author dmz
 * @date 2017/2/7
 */
public class SimpleLockStart {
    public static void main(String[] args) {
        final SimpleLock simpleLock = new SimpleLock();
        simpleLock.lock();
        for (int i = 0; i <10 ; i++) {
            new Thread(()->{
                simpleLock.lock();
                System.out.println(Thread.currentThread().getId()+" acquired.");
                simpleLock.unLock();
            }).start();
        }
        System.out.println("main thread lock release ");
        simpleLock.unLock();
    }
}
