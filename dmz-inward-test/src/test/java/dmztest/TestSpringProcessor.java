package dmztest;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author dmz
 * @date 2017/1/9
 */
public class TestSpringProcessor {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-process-beans.xml");
        System.out.println();
//        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
//        RootBeanDefinition beanDefinition = new RootBeanDefinition("${beanName}");//占位符
//        beanDefinition.setScope("${scop}");//占位符
//        factory.registerBeanDefinition("myBean",beanDefinition);
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("beanName", "Tester");//占位符对应
//        map.put("scop", RootBeanDefinition.SCOPE_PROTOTYPE);//占位符对应“prototype”
//        MutablePropertySources sources = new MutablePropertySources();
//        sources.addLast(new MapPropertySource("abc", map));
//
//        PropertySourcesPlaceholderConfigurer configurer
//                = new PropertySourcesPlaceholderConfigurer();
//        configurer.setPropertySources(sources);
//
//        //对 BeanFactory增强，会替换掉占位符
//        configurer.postProcessBeanFactory(factory);
//
//        //获取这个bean
//        System.out.println(factory.getBeanDefinition("myBean"));
//        System.out.println(factory.getBean("myBean"));

    }

}
