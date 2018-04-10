package com.dmz.service.distributelock;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author dmz
 * @date 2017/11/23
 */
//@Service
public class ServiceFlowImpl implements IServiceFlow, MutexProcessCallBack {

    @Resource
    private MutexLock mutexLock;

    @Override
    public void doMutexTest() {
        mutexLock.processExclusively(this);
    }

    @Override
    public void doTest() {
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

    @Override
    public Object process() {
        doTest();
        return null;
    }

    @Override
    public String getMutexProcessName() {
        return "Balance";
    }
}
