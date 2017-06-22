package dmztest;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import java.util.Locale;

/**
 * @author dmz
 * @date 2016/12/19
 */
public class MessageResourceInject implements MessageSourceAware {

    private MessageSource messageSource;

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void showMessage() {
        String message = messageSource.getMessage("msg.text", null, Locale.US);
        System.out.println(message);
    }
}
