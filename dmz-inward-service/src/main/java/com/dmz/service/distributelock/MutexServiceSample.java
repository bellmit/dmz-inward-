package com.dmz.service.distributelock;

/**
 * @author dmz
 * @date 2017/11/23
 */
public class MutexServiceSample implements MutexProcessCallBack<Void> {

    @Override
    public Void process() {
        return null;
    }

    @Override
    public String getMutexProcessName() {
        return null;
    }
}
