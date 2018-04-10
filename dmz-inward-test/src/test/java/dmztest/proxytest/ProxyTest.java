package dmztest.proxytest;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * @author dmz
 * @date 2018/1/24
 */
public class ProxyTest {
    public static void main(String[] args) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int compilationResult = compiler.run(null, null, null, "D:\\workplace\\Mine\\dmz-inward-\\dmz-inward-test\\src\\test\\java\\dmztest\\proxytest\\ProxyTest.java");
        System.out.println(compilationResult);
    }
}
