package com.dmz.service.distributelock;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.TimeUnit;

/**
 * @author dmz
 * @date 2017/11/23
 */
public abstract class MutexLockServer implements MutexLock, InitializingBean ,DisposableBean {


    protected long DEFAULT_TIMEOUT = 5;
    protected TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;


    private LockMode mode;

    abstract void initMutexLockService();

    abstract void shutDownMutexLockService();

    @Override
    public void afterPropertiesSet() throws Exception {
        this.initMutexLockService();
    }

    @Override
    public void destroy() throws Exception {
        this.shutDownMutexLockService();
    }

    enum LockMode {
        REDIS_SINGLE,
        REDIS_CLUSTER;

        public static LockMode parse(String name) {
            for (LockMode modeIns : LockMode.values()) {
                if (modeIns.name().equals(name.toUpperCase())) {
                    return modeIns;
                }
            }
            return null;
        }
    }
}
