package com.dmz.test.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * Created by dmz on 2016/12/6.
 */
public class FactoryBeanFake implements FactoryBean<Object> {

    @Override
    public Object getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
