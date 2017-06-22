package dmztest;

/**
 * Created by dmz on 2016/8/17.
 */
class SuperClass {
    static void disploy() {
        System.out.println("superClass...");
    }
}

class SubClass extends SuperClass {
   static void disploy() {
        System.out.println("subClass...");
    }
}

public class HidingMethodTest {

    public static void main(String[] args) {

        SuperClass superClass1 = new SuperClass();
        SubClass subClass1 = new SubClass();
        SuperClass superClass = new SubClass(); //方法隐藏

        superClass1.disploy();
        subClass1.disploy();
        superClass.disploy();

    }
}
