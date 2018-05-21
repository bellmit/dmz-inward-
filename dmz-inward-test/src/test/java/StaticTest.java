public class StaticTest {
    public static void main(String[] args)
    {
//        staticFunction();

//        Singleton.getInstance();
        Singleton.getInstance();
    }


//    static StaticTest st = new StaticTest();
//
//    static
//    {
//        System.out.println("1");
//    }
//
//    {
//        System.out.println("2");
//    }
//
//    StaticTest()
//    {
//        System.out.println("3");
//        System.out.println("a="+a+",b="+b);
//
//    }
//
//    public static void staticFunction(){
//        System.out.println("4");
//    }
//
//    int a=110;
//    static int b =112;
}


//class Singleton {
//    private static Singleton singleton = new Singleton();
//
//    private Singleton() {
//    }
//
//    public static Singleton getInstance() {
//        return singleton;
//    }
//}

//
//class Singleton {
//    private static Singleton singleton = new Singleton();
//
//    private Singleton() {
//        System.out.println("H");
//    }
//
//    public static Singleton getInstance() {
//        return singleton;
//    }
//}
//class Singleton {
//    private static Singleton singleton;
//
//    public static Singleton getInstance() {
//        if (singleton == null) {
//            synchronized (Singleton.class) {
//                if (singleton == null) {
//                    singleton = new Singleton();
//                }
//            }
//        }
//        return singleton;
//    }
//}


class Singleton {
    private static class SingletonHolder{
        static {
            System.out.println("Holder");
        }
        public static Singleton singleton = new Singleton();
    }

    private Singleton(){
        System.out.println("KKKKKKK");
    }
    public static Singleton getInstance(){
        return null;
    }
}