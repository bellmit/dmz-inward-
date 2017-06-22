package dmz.facade.mqTest;

import com.dmz.service.mq.IRocketMQService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by dmz on 2016/3/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-utils.xml","classpath:Spring-facade-provider.xml"})
public class IRocketMQServiceTest {

    @Resource(name="rocketMQService")
    private IRocketMQService rocketMQService;

    @Test
    public void testSendMessage(){
        rocketMQService.sendMessage("newdmz", "dmz-inward", "1", "LOVE INWARD DMZ");
        System.out.println("dmz.");
    }
}
