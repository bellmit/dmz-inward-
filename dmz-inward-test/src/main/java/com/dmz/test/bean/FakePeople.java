package com.dmz.test.bean;

import org.mockito.Mockito;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by dmz on 2016/12/6.
 */
public class FakePeople implements FactoryBean<People> {

    @Override
    public People getObject() throws Exception {
        return Mockito.mock(People.class);
    }

    @Override
    public Class<?> getObjectType() {
        return Person.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
