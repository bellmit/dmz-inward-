package dmztest.com.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by dmz on 2016/1/28.
 */
public class ReflectTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        //original create instance
//        MyClass myClass = new MyClass(10);
//        System.out.println(myClass.count);
//        myClass.inCrease(10);
//        System.out.println(myClass.count);

        Constructor<MyClass> constructor = MyClass.class.getConstructor(int.class);
        MyClass myClass = constructor.newInstance(10); // reflect create instance
        System.out.println(myClass.getCount());

        Method method = MyClass.class.getMethod("inCrease", int.class); //reflect get instance method
        method.invoke(myClass, 5); //调用方法
        System.out.println(myClass.getCount());

        Field field = MyClass.class.getField("count"); //获取域
        System.out.println("Reflect -> " + field.getInt(myClass)); //获取域的值
    }
}
