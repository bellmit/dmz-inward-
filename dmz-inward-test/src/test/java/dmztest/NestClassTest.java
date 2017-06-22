package dmztest;

/**
 * @author dmz
 * @date 2016/12/23
 */
public class NestClassTest {
    public static void main(String[] args) {

        NestClassStatic.SubClazz ns = new NestClassStatic.SubClazz();
        NestClassNotStatic.SubNotStaticClazz nns = new NestClassNotStatic().new SubNotStaticClazz();
    }

}
