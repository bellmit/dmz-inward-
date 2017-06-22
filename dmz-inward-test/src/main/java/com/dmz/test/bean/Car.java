package com.dmz.test.bean;

/**
 * Created by dmz on 2016/12/9.
 */
public class Car {
    private String name;

    public Car(String name) {
        this.name=name;
    }

    public void drive() {
        System.out.println("Drive "+name);
    }
}
