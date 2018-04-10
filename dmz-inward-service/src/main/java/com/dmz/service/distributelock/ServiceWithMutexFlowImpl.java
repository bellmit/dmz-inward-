package com.dmz.service.distributelock;

import com.dmz.service.annotation.BMutexLock;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author dmz
 * @date 2017/11/23
 */
@Service
public class ServiceWithMutexFlowImpl implements IServiceWithMutexFlow {

    @Override
    @BMutexLock(bLockName = "Balance")
    public void doTest() {
        System.out.println(Thread.currentThread().getName() + " start");
        Random random = new Random();
        int _int = random.nextInt(200);
        System.out.println(Thread.currentThread().getName() + " sleep "  +_int+ "millis");
        try {
            Thread.sleep(_int);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " end");
    }

    @Override
    public void doTest2() {

    }


}
