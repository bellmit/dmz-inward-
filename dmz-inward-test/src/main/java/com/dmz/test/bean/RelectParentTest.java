package com.dmz.test.bean;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by dmz on 2016/12/7.
 */
public class RelectParentTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        Constructor<ReflectParent> constructor = ReflectParent.class.getConstructor(null);
        ReflectParent reflectParent = constructor.newInstance();

//        Field field = ReflectParent.class.getDeclaredField("reflectSun");
        ReflectParent.class.getField("reflectSun"); //此方法只能读取public的字段名字
//        或者
        Field field = reflectParent.getClass().getDeclaredField("reflectSun");

        field.setAccessible(true);
        field.set(reflectParent,new ReflectSun());
        reflectParent.sayParent();

    }
}
