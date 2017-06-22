package com.dmz.service.constant.basic;

import java.lang.annotation.*;

/**
 * Created by dmz on 2016/3/31.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginDetail {
    boolean login() default false;
}
