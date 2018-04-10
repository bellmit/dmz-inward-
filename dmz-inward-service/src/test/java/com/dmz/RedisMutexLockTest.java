package com.dmz;

import com.dmz.basic.config.DataConfig;
import com.dmz.basic.config.PropertiesConfig;
import com.dmz.service.config.ServiceConfig;
import com.dmz.service.distributelock.IServiceWithMutexFlow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author dmz
 * @date 2017/11/23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceConfig.class,PropertiesConfig.class, DataConfig.class})
public class RedisMutexLockTest {

    //@Resource
    //private IServiceFlow service;
    //
    //@Resource
    //private IServiceWithMutexLock serviceWithMutexLock;

    @Resource
    private IServiceWithMutexFlow serviceWithMutexFlow;

    //@Test
    //public void testIServiceFlow() throws InterruptedException {
    //    CountDownLatch countDownLatch = new CountDownLatch(5);
    //    for (int i=0;i<5;i++) {
    //        //new Thread(() -> service.doMutexTest()).start();
    //        new Thread(() -> {service.doMutexTest();            // CallBack
    //        countDownLatch.countDown();
    //        }).start();
    //    }
    //    countDownLatch.await();
    //}
    //
    //@Test
    //public void testIServiceWithMutexLock() throws InterruptedException, IOException {
    //
    //    CountDownLatch countDownLatch = new CountDownLatch(5);
    //    CountDownLatch singleLatch = new CountDownLatch(1);
    //    for (int i = 0; i < 5; i++) {
    //        new Thread(()->{
    //            serviceWithMutexLock.doTest(singleLatch);
    //            countDownLatch.countDown();
    //        }).start();
    //    }
    //    singleLatch.countDown();
    //    countDownLatch.await();
    //}

    @Test
    public void testServiceWithMutexFlow() throws InterruptedException, IOException {

        CountDownLatch countDownLatch = new CountDownLatch(100);
        //CountDownLatch singleLatch = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                serviceWithMutexFlow.doTest();
                countDownLatch.countDown();
            }).start();
        }
        //singleLatch.countDown();
        countDownLatch.await();
    }
}
