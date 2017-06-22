package dmz.facade.dubboTest;

import com.dmz.service.dubbo.invoke.YmUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by dmz on 2016/3/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-facade-consumer.xml"})
public class DubboConsumerTest {

    @Autowired
    private YmUser ymUser;

    @Test
    public void testDubboConsumer(){
        System.out.println(ymUser.showName());
    }
}
