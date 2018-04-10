package com.dmz.service.distributelock;

/**
 * @author dmz
 * @date 2017/11/23
 */
public interface MutexProcessCallBack<T> {
    T process();

    String getMutexProcessName();
}
