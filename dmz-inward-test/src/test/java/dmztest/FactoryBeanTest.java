package dmztest;

import com.dmz.test.bean.ClassRoom;
import com.dmz.test.bean.ConfigurationBean;
import com.dmz.test.bean.IPerson;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by dmz on 2016/12/2.
 */
public class FactoryBeanTest {
    //加载资源文件下
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("SpringBean.xml");
    //加载项目路径下
//        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("SpringBean3.xml");
    //覆盖默认加载bean的Resource
//          FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("classpath:SpringBean.xml");
    //从Class路径下加载
//        ClassPathXmlApplicationContext ctx =
//                new ClassPathXmlApplicationContext(
//                new String[] {"SpringBean3.xml"}, ResourceTest.class);
    //classloader加载资源文件
//        URL a = new ApplicationContext().getClass().getClassLoader().getResource("SpringBean.xml");

    public static void main(String[] args) {
//        以下的加载方式不建议使用!!!
//        ClassPathResource resource = new ClassPathResource("spring-beans.xml");
//        DefaultListableBeanFactory context2 = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader  reader = new XmlBeanDefinitionReader(context2);
//        reader.loadBeanDefinitions(resource);


        BeanFactory context = new ClassPathXmlApplicationContext("spring-beans.xml");

        IPerson person = context.getBean("person", IPerson.class);
        person.sayHello();
        IPerson per = context.getBean("logPerson", IPerson.class);
        per.sayHello();

        ClassRoom classRoom = context.getBean("classRoom", ClassRoom.class);

        System.out.println( context.getBean("logPerson").getClass());
        System.out.println(context.getBean("person").getClass());
        System.out.println("-------------------------------------");
        System.out.println(context.getBean(BeanFactory.FACTORY_BEAN_PREFIX+"logPerson").getClass());
        System.out.println("-------------------------------------");


        System.out.println("-----------------Configuration Beans--------------------");
        ConfigurationBean cbean = context.getBean("cbean", ConfigurationBean.class);
        cbean.say();
//        ClassPathResource resource = new ClassPathResource("spring-beans.xml");
//        XmlBeanFactory beanFactory = new XmlBeanFactory(resource);
//        System.out.println(beanFactory.getBean("logPerson").getClass());
//        System.out.println(beanFactory.getBean("person").getClass());
//        System.out.println(beanFactory.getBean("&logPerson").getClass());
    }
}
