package dmztest;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * Created by dmz on 2016/10/25.
 */
public class CurrencyDescription {
    public static void main(String[] args) {

        double currencyAmount = 123456.78901;
        float totalAmount = 54321.1234f;
        Locale locale = Locale.getDefault();
        locale = Locale.CHINA;
        locale = Locale.US;
        Currency currentCurrency = Currency.getInstance(locale);
        NumberFormat currentFormatter = NumberFormat.getCurrencyInstance(locale);
//        currentFormatter.setMaximumIntegerDigits(3);
        currentFormatter.setMaximumFractionDigits(3);

        System.out.println(locale.getDisplayName());
        System.out.println(currentCurrency.getDisplayName());
        System.out.println(currentFormatter.format(currencyAmount));

    }
}
