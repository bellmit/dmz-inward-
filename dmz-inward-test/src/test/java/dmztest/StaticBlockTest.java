package dmztest;

/**
 * Created by dmz on 2016/8/2.
 */
public class StaticBlockTest {

    private static class HelloA{


        { System.out.println("I'm A class"); }
        { System.out.println("I'm A class 2"); }
        static {
            System.out.println("static A");
        }

        public HelloA(){
            System.out.println("HelloA");
        }


    }
    private static class HelloB extends HelloA{
        public HelloB(){
            System.out.println("HelloB");
        }

        { System.out.println("I'm B class"); }

        static {
            System.out.println("static B");
        }

        public static void main(String[] args) {
//            static A    I'm A class static B I'am B class HelloA HelloB I'm A class I'm B class HelloA HelloB
            System.out.println("main start");
            new HelloB();
            new HelloB();
            System.out.println("main end");
        }
    }
}
