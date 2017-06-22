package dmztest;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author dmz
 * @date 2016/12/20
 */
public class ResourceBundleTest {
    public static void main(String[] args) {

        ResourceBundle bundleUS = ResourceBundle.getBundle("messages", Locale.US);
        System.out.println("US:" + bundleUS.getString("msg.text"));

        ResourceBundle bundleCN = ResourceBundle.getBundle("messages", Locale.CHINA);
        System.out.println("CN:" + bundleCN.getString("msg.text"));

        Object[] params = {"dmz", Calendar.getInstance().getTime()};
        MessageFormat messageFormat = new MessageFormat(bundleUS.getString("msg.text"), Locale.US);
        String message = messageFormat.format(params);
        System.out.println(message);
    }
}
