package dmztest;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author dmz
 * @date 2016/12/19
 */
public class LocaleMessageTest {
    public static void main(String[] args) {
        String pattern1 = "{0}，你好！你于{1}在工商银行存入{2} 元。";
        Object[] params = {"Johon", new Date().getTime(), 1.0E3};
        String message = MessageFormat.format(pattern1, params);
        System.out.println(message);

        Locale locale = Locale.CHINA;
        String pattern2 = "At {1,time,short} On {1,date,long}，{0} paid {2,number, currency}.";
        MessageFormat messageFormat = new MessageFormat(pattern2, locale);
        String message2 = messageFormat.format(params);
        System.out.println(message2);

        System.out.println(Locale.getDefault());
    }
}
