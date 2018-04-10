package com.dmz.service.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author dmz
 * @date 2017/11/24
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BMutexLock {

    String bLockName();

    long leaseTime() default 30;  // 秒

    long waitTime() default 10; //秒

    TimeUnit timeUnit() default TimeUnit.SECONDS;

    boolean fairLock() default false;
}
