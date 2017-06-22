package dmztest.com.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by dmz on 2016/1/25.
 */
public class AnotherExampleInitBean {
    public AnotherExampleInitBean(){
        System.out.println("Init AnotherExampleInitBean at starting...");
    }

    //bean加载完成后的动作
    @PostConstruct
    public void initAnotherExampleInitBean() {
        System.out.println("AnotherExampleInitBean arterPropertiesSet...");
    }

    @PreDestroy
    public void cleanUp(){
        System.out.println("clean up AnotherExampleInitBean...");
    }
}
