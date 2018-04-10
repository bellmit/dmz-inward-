package com.dmz.service.distributelock;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * @author dmz
 * @date 2017/11/23
 */
public interface MutexLock {

    <T> T processExclusively(MutexProcessCallBack<T> callback);

    <T> T processExclusively(MutexProcessCallBack<T> callback, long leaseTime, TimeUnit timeUnit);

    boolean lock(String lockName);

    boolean lock(String lockName, long leaseTime, TimeUnit timeUnit);

    boolean tryLock(String lockName, long waitTime, long leaseTime, TimeUnit unit);

    RLock getLock(String lockName,long waitTime, long leaseTime, TimeUnit unit, boolean fairLock);

    void release(String lockName);

    void forceRelease(String lockName);
}
