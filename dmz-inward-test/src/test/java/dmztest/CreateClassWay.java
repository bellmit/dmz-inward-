package dmztest;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by dmz on 2016/11/2.
 */
class People implements Cloneable,Serializable {
    private String name;

    public People() {
        System.out.println("I am public Constructor.");
    }

    public People(String name) {
        this.name = name;
        System.out.println("I am public Constructor With Args.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
public class CreateClassWay {
    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("net.frontnode.openapi.People");
            People people = (People) clazz.newInstance();
//            System.out.println("people----->"+people.getName());

//            --------------------------------------------------------
            People clazz1 = (People) CreateClassWay.class.getClassLoader().loadClass("net.frontnode.openapi.People").newInstance();
//            System.out.println("clazz1----->"+clazz1.getName());
//            --------------------------------------------------------
            People people1 = new People();
            People people2 = (People)people1.clone();
//            System.out.println("people2----->"+people2.getName());
//            --------------------------------------------------------
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("HHHHHHH.H"));
            objectOutputStream.writeObject(people1);
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("HHHHHHH.H"));
            People people3 = (People) objectInputStream.readObject();
//            System.out.println("people3----->"+people3.getName());
//            -------------------------------------------------------
            Constructor<?>[] constructors = People.class.getDeclaredConstructors();
            Constructor constructor = null;
            for (int i=0;i<constructors.length;i++) {
//                System.out.println(constructors[i].getName() + " " + constructors[i].getGenericParameterTypes().length);
                constructor = constructors[i];
                if (constructor.getGenericParameterTypes().length == 0) {
                    break;
                }
            }
            constructor.setAccessible(true); //设置构造器的访问权限
            People people4 = (People) constructor.newInstance();
            System.out.println(people4.getName());
//            ------------------------------------------------------------------------------
            Constructor<People> constructor1 = People.class.getDeclaredConstructor(String.class);
            People people5 = constructor1.newInstance("DMZ");
            System.out.println(people5.getName());
            Field field = constructor1.getClass().getDeclaredField("name");
            Object name = field.get(constructor1);
            System.out.println(name);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}
