package com.dmz.service.advisor;

import com.dmz.service.annotation.BMutexLock;
import com.dmz.service.distributelock.MutexLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author dmz
 * @date 2017/11/24
 */
@Component
@Aspect
public class BMutexLockAdvisor {

    private static final Logger LOGGER = LoggerFactory.getLogger(BMutexLockAdvisor.class);

    @Resource
    private MutexLock mutexLock;


    @Resource
    private RedissonClient redissonClient;

    //@Pointcut(value = "execution(* com.dmz.service.distributelock.*.*(..)) && @annotation(com.dmz.service.annotation.BMutexLock)")
    //private void bProcessMutexPoint() {
    //}
    //
    ////@Pointcut(value = "@annotation(com.dmz.service.annotation.BMutexLock)")
    //@Pointcut(value="@annotation(com.dmz.service.annotation.BMutexLock)")
    //private void bProcessMutexPointT() {
    //}

    @Pointcut(value = "execution(@com.dmz.service.annotation.BMutexLock * com.dmz.service.distributelock.*.*(..))")
    private void mutexProcessMutex() {
    }

    @Around("mutexProcessMutex() && @annotation(balanceMutexLock)")
    public void mutexProcessBalance(ProceedingJoinPoint joinPoint, BMutexLock balanceMutexLock) {


        boolean acquired = false;
        int retries = 0;
        int maxRetries = 10;
        long wait = balanceMutexLock.waitTime();

        try {
            while (!acquired && retries < maxRetries) {
                acquired = mutexLock.tryLock(balanceMutexLock.bLockName(),wait, balanceMutexLock.leaseTime(), balanceMutexLock.timeUnit());
                wait *= 2;
                ++retries;
            }
            if (!acquired) {
                // log error or throw exception
            }
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            mutexLock.release(balanceMutexLock.bLockName());
        }
        // No Retry Time.
        //try {
        //
        //    if (mutexLock.lock(balanceMutexLock.bLockName(), balanceMutexLock.leaseTime(), balanceMutexLock.timeUnit())) {
        //        //获得锁
        //        joinPoint.proceed();
        //    }
        //
        //    //if (
        //    //mutexLock.tryLock(balanceMutexLock.bLockName(), balanceMutexLock.waitTime(), balanceMutexLock.leaseTime(), balanceMutexLock.timeUnit());
        //    //
        //    //        ) {
        //    //    joinPoint.proceed();
        //    //}
        //} catch (Throwable throwable) {
        //    LOGGER.error("HHHHHHHHHHHHHHH {}", throwable);
        //} finally {
        //    mutexLock.release(balanceMutexLock.bLockName());
        //}

    }
}
