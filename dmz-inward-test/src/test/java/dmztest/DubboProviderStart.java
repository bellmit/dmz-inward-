package dmztest;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author dmz
 * @date 2017/3/13
 */
public class DubboProviderStart {
    public static void main(String[] args) {
        BeanFactory context = new ClassPathXmlApplicationContext("Spring-facade-provider.xml");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
