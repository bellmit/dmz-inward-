package dmztest;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by dmz on 2016/8/31.
 */
public class LocaleExample {
    public static void main(String[] args) {
        long number = 12345678L;
        Date now = new Date();
        Locale local = Locale.CHINA;
        NumberFormat numberFormat = NumberFormat.getInstance(local);
        System.out.println(numberFormat.format(number));
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, local);
        System.out.println(df.format(now));


        Locale.setDefault(Locale.Category.DISPLAY, Locale.US);
        Locale.setDefault(Locale.Category.FORMAT, Locale.FRANCE);

//        ResourceBundle resourceBundle =  ResourceBundle.getBundle("bundleName", Locale.getDefault(Locale.Category.DISPLAY));
//        String message = resourceBundle.getString("message.key");
//
//        Date now = new Date();
//        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
//        String date = df.format(now);
    }
}
