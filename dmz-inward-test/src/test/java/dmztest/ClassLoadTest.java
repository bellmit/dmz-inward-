package dmztest;

/**
 * @author dmz
 * @date 2017/3/3
 */
class AA {

    static {
        System.out.println("HHHHHHH");
    }

    private static int anInt = getB();

    public static int getA() {
        System.out.println("A");
        return 10;
    }

    public static int getB() {
        System.out.println("B");
        return 10;
    }
}

public class ClassLoadTest {


    public static void main(String[] args) {
        System.out.println(AA.getA() + "B");
    }


}
