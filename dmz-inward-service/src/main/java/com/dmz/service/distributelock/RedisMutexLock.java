package com.dmz.service.distributelock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author dmz
 * @date 2017/11/23
 */

public @Service
class RedisMutexLock extends MutexLockServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisMutexLock.class);

    private static Map<String, RLock> nameRlock = new HashMap();

    private static String REDIS_LOCK = "redis_lock";

    @Resource
    private RedissonClient redissonClient;


    @Override
    public <T> T processExclusively(MutexProcessCallBack<T> callback) {
        return processExclusively(callback, DEFAULT_TIMEOUT, DEFAULT_TIME_UNIT);
    }

    @Override
    public <T> T processExclusively(MutexProcessCallBack<T> callback, long leaseTime, TimeUnit timeUnit) {

        RLock lock = null;
        try {
            lock = redissonClient.getLock(callback.getMutexProcessName());
            lock.lock(leaseTime, timeUnit);
            return callback.process();
        } finally {
            if (lock != null) {
                lock.unlock();
            }
        }
    }

    @Override
    public boolean lock(String lockName) {
        try {
            RLock rLock = redissonClient.getLock(REDIS_LOCK + lockName);
            rLock.lock();
            return true;
        } catch (Exception e) {
            LOGGER.error("Redis Lock Error :{}", e);
            return false;
        }
    }

    @Override
    public boolean lock(String lockName, long leaseTime, TimeUnit timeUnit) {
        try {
            RLock rLock = redissonClient.getLock(REDIS_LOCK + lockName);
            rLock.lock(leaseTime, timeUnit);
            return true;
        } catch (Exception e) {
            LOGGER.error("Redis Lock Error :{}", e);
            return false;
        }
    }

    @Override
    public boolean tryLock(String lockName, long waitTime, long leaseTime, TimeUnit unit) {
        try {
            RLock rLock = redissonClient.getLock(REDIS_LOCK + lockName);
            return rLock.tryLock(waitTime, leaseTime, unit);
        } catch (Exception e) {
            LOGGER.error("Redis Lock Error :{}", e);
            return false;
        }
    }

    @Override
    public RLock getLock(String lockName, long waitTime, long leaseTime, TimeUnit unit, boolean fairLock) {
        RLock lock = null;
        try {
            if (fairLock) {
                lock = redissonClient.getFairLock(lockName);
            } else {
                lock = redissonClient.getLock(lockName);
            }
            lock.tryLock(waitTime, leaseTime, unit);
        } catch (Exception e) {
            LOGGER.error("get lock error {}", e);
        }
        return lock;
    }

    @Override
    public void release(String lockName) {

        RLock rLock = null;
        try {
            rLock = redissonClient.getLock(REDIS_LOCK + lockName);
        } catch (Exception e) {
            LOGGER.error("Redis UnLock Error :{}", e);
        } finally {
            if (rLock != null) {
                rLock.unlock();
            }
        }
    }

    @Override
    public void forceRelease(String lockName) {

        RLock rLock = null;
        try {
            rLock = redissonClient.getLock(REDIS_LOCK + lockName);
        } catch (Exception e) {
            LOGGER.error("Redis UnLock Error :{}", e);
        } finally {
            if (rLock != null) {
                rLock.forceUnlock();
            }
        }

    }

    @Override
    void initMutexLockService() {

    }

    @Override
    void shutDownMutexLockService() {
        if (redissonClient != null) {
            redissonClient.shutdown();
        }
    }
}
