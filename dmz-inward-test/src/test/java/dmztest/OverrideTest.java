package dmztest;

import java.io.IOException;

/**
 * Created by dmz on 2016/8/17.
 */
class A {
    public void a() throws IOException {
        System.out.println("a");
    }
}
class B extends A{
    public void a() throws RuntimeException  {
        System.out.println("b");
    }
}

public class OverrideTest {
    public static void main(String[] args) throws Exception {
        A b = new B();
        b.a();

        A a = new A();
        a.a();
    }
}
