package com.dmz;

import org.mockito.Mockito;

/**
 * @author dmz
 * @date 2017/2/24
 */
public class TestFactoryBean {

    public static Object getMockBean(String name) {
        Object mockClass = null;
        try {
            Class<?> clazz = Class.forName(name);
            mockClass = Mockito.mock(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return mockClass;
    }
}
