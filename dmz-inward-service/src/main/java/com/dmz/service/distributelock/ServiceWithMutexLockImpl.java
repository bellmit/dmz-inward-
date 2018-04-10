package com.dmz.service.distributelock;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author dmz
 * @date 2017/11/23
 */
//@Service
public class ServiceWithMutexLockImpl implements IServiceWithMutexLock {

    @Resource
    private MutexLock mutexLock;

    @Override
    public void doTest(CountDownLatch countDownLatch) {
        try {

            // BUSINESS

            if (mutexLock.lock("test")) {
                countDownLatch.await();
                doTestBusiness();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mutexLock.release("test");
        }
    }

    @Override
    public void doTest2(CountDownLatch countDownLatch) {
        try {

            // BUSINESS2
            countDownLatch.await();
            doTestBusiness();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mutexLock.release("test");
        }
    }


    private void doTestBusiness() {
        System.out.println(Thread.currentThread().getName() + " start");
        Random random = new Random();
        int _int = random.nextInt(200);
        System.out.println(Thread.currentThread().getName() + " sleep " + _int + "millis");
        try {
            Thread.sleep(_int);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " end");
    }
}
