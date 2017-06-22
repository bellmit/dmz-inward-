package com.dmz.test.bean;

import org.springframework.beans.factory.FactoryBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dmz on 2016/12/9.
 */
public class FactoryClassTest implements FactoryBean<Object> {

    private Map<String, Car> cars = new HashMap<>();

    public FactoryClassTest() {
        cars.put("tesla", new Car("tesla"));
    }

    public Car getCar(String name) {
        return cars.get(name);
    }

    @Override
    public Object getObject() throws Exception {
        return this;
    }

    @Override
    public Class<?> getObjectType() {
        return FactoryClassTest.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}

