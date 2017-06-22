package dmztest.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dmz
 * @date 2017/2/6
 */

//Ticket锁主要解决的是访问顺序的问题,Fair Lock.
public class TicketLock {

    private AtomicInteger serviceNum = new AtomicInteger();
    private AtomicInteger ticketTime = new AtomicInteger();
    private static final ThreadLocal<Integer> local = new ThreadLocal<>();

    public void lock() throws InterruptedException {

        int ticket = ticketTime.getAndIncrement();
        local.set(ticket);
        while (ticket != serviceNum.get()) {
            System.out.println("waiting....");
            Thread.sleep(1000);
        }

    }

    public void unLock() {
        int ticket = local.get();
        serviceNum.compareAndSet(ticket, ticket + 1);
    }

}
