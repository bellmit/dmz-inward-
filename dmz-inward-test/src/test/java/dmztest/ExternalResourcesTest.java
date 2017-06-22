package dmztest;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author dmz
 * @date 2016/12/19
 */
public class ExternalResourcesTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-beans.xml");
//        CustomResourceLoader customResource = applicationContext.getBean("customResource", CustomResourceLoader.class);
//        customResource.showResources();
//        System.out.println("=====================");
        CustomResourceInjection resource = applicationContext.getBean("resourceInject", CustomResourceInjection.class);
//        resource.showResource();
    }
}
