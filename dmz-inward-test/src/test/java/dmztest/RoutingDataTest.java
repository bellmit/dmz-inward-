package dmztest;

import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author dmz
 * @date 2016/12/27
 */
public class RoutingDataTest {
    public static void main(String[] args) {
        String language = LocaleContextHolder.getLocale().getCountry();
        System.out.println(language);
    }
}
