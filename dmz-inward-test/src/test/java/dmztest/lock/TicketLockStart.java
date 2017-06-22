package dmztest.lock;

/**
 * @author dmz
 * @date 2017/2/6
 */
public class TicketLockStart {


    public static void main(String[] args) throws InterruptedException {
        final  TicketLock ticketLock = new TicketLock();
        final SimpleFairLock simpleFairLock = new SimpleFairLock();
        for (int i=0;i<10;i++) {
            new Thread(()->{
                try {
                    simpleFairLock.lock();
                    System.out.println(Thread.currentThread().getName()+" acquired the lock!");
                    simpleFairLock.unLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"Thread#"+i).start();
        }
    }
}
