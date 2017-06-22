package dmztest.Test;

import junit.framework.TestCase;
import org.mockito.Mockito;

/**
 * @author dmz
 * @date 2017/1/3
 */
class AStatic {
    public static int get() {
        return 10;
    }
}
interface B{
    public void setAnInt();

    public void showA();
}
class A implements B{
    private int anInt;

    public void setAnInt() {
        this.anInt = AStatic.get();
    }

    public void showA() {
        System.out.println(anInt);
    }
}

public class MockitoTest extends TestCase {


    public void test() {
        B a = Mockito.spy(A.class);
        a.setAnInt();
        a.showA();
    }
}
