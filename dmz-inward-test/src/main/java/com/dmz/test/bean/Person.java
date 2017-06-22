package com.dmz.test.bean;

import java.util.Properties;

/**
 * Created by dmz on 2016/12/2.
 */
public class Person implements IPerson {

    private String name;

    private Properties properties;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void sayHello() {
        System.out.println("I am " + name);
    }
}
