package dmztest.Properties;

/**
 * @author dmz
 * @date 2017/3/13
 */
public class SystemProtertyTest {
    public static void main(String[] args) {
        String keepAlive = System.getProperty("http.keepAlive");
        System.out.println(keepAlive);
    }
}
