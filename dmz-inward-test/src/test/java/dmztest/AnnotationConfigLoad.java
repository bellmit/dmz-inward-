package dmztest;

import com.dmz.test.bean.ConfigurationBean;
import com.dmz.test.bean.ConfigurationBeans;
import com.dmz.test.bean.IPerson;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by dmz on 2016/12/9.
 */
public class AnnotationConfigLoad {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigurationBeans.class);
        ConfigurationBean cbean = applicationContext.getBean("cbean", ConfigurationBean.class);
        cbean.say();
        ConfigurationBean cbean2 = applicationContext.getBean("cbean2", ConfigurationBean.class);
        cbean2.say();

        IPerson per = applicationContext.getBean("logPerson", IPerson.class);
        per.sayHello();
    }
}
