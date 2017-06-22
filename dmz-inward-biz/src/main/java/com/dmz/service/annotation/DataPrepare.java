package com.dmz.service.annotation;

import java.lang.annotation.*;

/**
 * Created by dmz on 2016/12/8.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface DataPrepare {

    String dataSource() default  "dataSource";

    String setDataSql() default "";

    String[] setDataLocations() default {};
}
