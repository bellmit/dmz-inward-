package dmztest.com.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by dmz on 2016/1/29.
 */
public class ReflectWeatherTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?> classWeather = Class.forName("com.reflect.WeatherImpl").getConstructor(new Class[0]);
        IWeather instance = (IWeather)classWeather.newInstance();
        instance.rain();
    }
}
