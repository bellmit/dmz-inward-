package dmztest.com.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by dmz on 2016/1/25.
 */
public class ExampleInitBean implements InitializingBean, DisposableBean {
    public ExampleInitBean() {
        System.out.println("Init ExampleInitBean at starting...");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("1----1ExampleInitBean arterPropertiesSet...");
    }

    @PostConstruct
    public void setPropertiesAfter() {
        System.out.println("3----3ExampleInitBean arterPropertiesSet...");
    }

    public void destroy() throws Exception {
        System.out.println("1----1clean up ExampleInitBean...");
    }

    @PreDestroy
    public void cleanUp() {
        System.out.println("3----3clean up ExampleInitBean...");
    }

    public void init(){
        System.out.println("2----2ExampleInitBean arterPropertiesSet...");
    }
    public void des(){
        System.out.println("2----2clean up ExampleInitBean...");
    }
}
