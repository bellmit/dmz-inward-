package dmztest.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author dmz
 * @date 2017/2/7
 */
public class SimpleLock extends AbstractQueuedSynchronizer {

    private static final long serialVersionUID = -6505918379834413144L;

    @Override
    protected boolean tryAcquire(int arg) {
        if (compareAndSetState(0, 1)) {
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean tryRelease(int arg) {
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    public void lock() {
        acquire(1);
    }

    public void unLock() {
        release(1);
    }
}
