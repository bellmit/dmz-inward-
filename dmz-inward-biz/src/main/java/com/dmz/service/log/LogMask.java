package com.dmz.service.log;

import java.lang.annotation.*;

/**
 * @author dmz
 * @date 2017/6/20
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogMask {

    int maskStart() default 0;

    int maskEnd() default 0;
}
