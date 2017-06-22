package com.dmz.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dmz on 2016/12/9.
 */
/*静态方法工厂类*/
public class StaticFactoryClassTest {

    private static Map<String, Car> cars = new HashMap<>();


    static {
        cars.put("audi", new Car("audi"));
        cars.put("ford", new Car("ford"));
    }

    public static Car getCar(String name) {
        return cars.get(name);
    }

    public static Car getCar() {
        return cars.get("ford");
    }

}
