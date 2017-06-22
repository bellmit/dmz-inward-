package dmztest;

import com.dmz.service.dubbo.invoke.UserDmz;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author dmz
 * @date 2017/3/13
 */
public class DubboConsumerStart {
    public static void main(String[] args) {
        BeanFactory context = new ClassPathXmlApplicationContext("Spring-facade-consumer.xml");
        UserDmz userDmz = context.getBean("ym", UserDmz.class);
        System.out.println(userDmz.showUserName());

    }
}
