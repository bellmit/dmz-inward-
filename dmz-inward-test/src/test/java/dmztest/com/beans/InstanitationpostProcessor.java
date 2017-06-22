package dmztest.com.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by dmz on 2016/1/26.
 */
public class InstanitationpostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object o, String beanName) throws BeansException {
        System.out.println("BeforeInitializationBean '" + beanName + "' created : " + o.toString());
        return o;
    }

    public Object postProcessAfterInitialization(Object o, String beanName) throws BeansException {
        System.out.println("AfterInitializationBean '" + beanName + "' created : " + o.toString());
        return o;
    }
}
