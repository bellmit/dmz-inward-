package dmztest;

import com.dmz.test.bean.Car;
import com.dmz.test.bean.ClassRoomFake;
import com.dmz.test.bean.People;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by dmz on 2016/12/9.
 */
public class FactoryClassTestLoad {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-factory-bean.xml");
        Car carOne = applicationContext.getBean("carOne", Car.class);
        carOne.drive();
        Car carTwo = applicationContext.getBean("carTwo", Car.class);
        carTwo.drive();
        ClassRoomFake classRoom = applicationContext.getBean("classRoomFake", ClassRoomFake.class);
        People people = applicationContext.getBean("com.dmz.test.bean.People#0", People.class);
        System.out.println(classRoom);
    }
}
