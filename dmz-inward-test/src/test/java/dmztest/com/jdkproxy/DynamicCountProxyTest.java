package dmztest.com.jdkproxy;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by dmz on 2016/1/28.
 */
public class DynamicCountProxyTest {
    public static void main(String[] args) throws FileNotFoundException {
//        DynamicCountProxy proxy = new DynamicCountProxy();
//        Count count = (Count)proxy.bind(new DynamicCount());
//        count.update();
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        DynamicCountProxy2 proxy = new DynamicCountProxy2();
        Count count = (Count) proxy.bind(new DynamicCount());
        count.update();

//        FileOutputStream file = new FileOutputStream("$Proxy0" + ".class");

        System.out.println(new File("$Proxy0" + ".class").getAbsolutePath());
    }
}
