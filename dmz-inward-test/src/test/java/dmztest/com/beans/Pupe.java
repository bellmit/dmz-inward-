package dmztest.com.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by dmz on 2016/1/27.
 */
@Component
public class Pupe {
    @Autowired
    private ApplicationContext context;
    public void getContext(){
        System.out.println(this.context);
    }
}
