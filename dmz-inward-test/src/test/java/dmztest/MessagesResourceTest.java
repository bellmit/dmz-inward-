package dmztest;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.util.Calendar;
import java.util.Locale;

/**
 * @author dmz
 * @date 2016/12/19
 */
public class MessagesResourceTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext application = new ClassPathXmlApplicationContext("spring-beans.xml");
        Object[] params = {"DMZ", Calendar.getInstance().getTime()};
        String message = application.getMessage("msg.text",params ,"无效的MSG", Locale.US);
        System.out.println(message);
        String message_CN = application.getMessage("msg.text", null, Locale.CHINA);
        System.out.println(message_CN);
        MessageResourceInject messageResource = application.getBean("messageLoader", MessageResourceInject.class);
        messageResource.showMessage();
        System.out.println("===========================================================");
        String[] codes = {"msg.text"};
        MessageSourceResolvable messageSourceResolvable = new DefaultMessageSourceResolvable(codes, params, "MSG无效");
        String messages = application.getMessage(messageSourceResolvable, Locale.US);
        System.out.println(messages);

    }
}
