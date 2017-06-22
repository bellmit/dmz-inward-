package dmztest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author dmz
 * @date 2016/12/29
 */
public class ModTest {
    public static void main(String[] args) {

        int a = 30;
        int b = 80;
        System.out.println(a / a);
        System.out.println(b / a);
        System.out.println(-30 % 2);
        Map<String, Integer> factors = new HashMap<>();
        factors.put("read01", 1);
        factors.put("read02", -1);
        Iterator<Map.Entry<String, Integer>> ite = factors.entrySet().iterator();
        while (ite.hasNext()) {
            Map.Entry<String, Integer> factor = ite.next();
            if (factor.getValue() <= 0) {
                ite.remove();
            }
        }
        System.out.println(factors);
    }
}
