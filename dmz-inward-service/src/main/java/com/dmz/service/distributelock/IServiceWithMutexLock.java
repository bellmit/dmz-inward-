package com.dmz.service.distributelock;

import java.util.concurrent.CountDownLatch;

/**
 * @author dmz
 * @date 2017/11/23
 */
public interface IServiceWithMutexLock {

    void doTest(CountDownLatch countDownLatch);

    void doTest2(CountDownLatch countDownLatch);
}
