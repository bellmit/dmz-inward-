package dmztest;

/**
 * Created by dmz on 2016/8/2.
 */



class TITLE{
    public static void Test() {
        System.out.println("TITLE");
    }
}
class StringExample1{
    String str = new String("good");
    char[] ch = {'a', 'b', 'c'};
    public void change(String str, char[] ch){
        str = "test ok";
        ch[0] = 'g';
    }
}
public class TTEST {

    private static class ForLoop{
        static boolean foo(char c){
            System.out.print(c);
            return true;
        }

        public static void main(String[] args) {
//            ABDCBDCB
            int i=0;
            for(foo('A');foo('B')&&(i<2);foo('C')){
                i++;
                foo('D');
            }
            System.out.println();
        }
    }
    private static void hello(){
        System.out.println("hello");

    }

    public static void change(String str){
        str = "welcome";
    }
    public static void main(String[] args) {
//        ((TTEST)null).hello();

//        StringExample1 ex = new StringExample1();
//        ex.change(ex.str, ex.ch);
//        System.out.print(ex.str+" and ");
//        System.out.print(ex.ch);
//        System.out.println();

//        String str = "1234";
//        change(str);
//        System.out.println(str);

        ForLoop.main(null);
    }
}
