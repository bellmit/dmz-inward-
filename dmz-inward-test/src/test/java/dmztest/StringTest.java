package dmztest;

/**
 * Created by dmz on 2016/8/3.
 */
interface Move {
    default void move() {
        System.out.println("move");
    }
}

interface Zou extends Move {
    int a = 10;

    default void move() {
        System.out.println("zou");
    }
}

public class StringTest implements Zou, Move {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

//        String a = "a";
//        String b = "a";
//        String c = new String("a").intern(); // two objects in memory,
//        System.out.println(a == b);
//        System.out.println(a == c);
//
//        String aa = new String("aa");
//        String bb = new String("aa");
//        System.out.println(aa == bb);

//        String mStr = "I_Love_Java";
//        String subStr = mStr.substring(7);
//        System.out.println(mStr);
//        System.out.println(subStr);
//        Field innerChar = String.class.getDeclaredField("value");
//        innerChar.setAccessible(true);
//        char[] chars = (char[]) innerChar.get(mStr);
//        System.out.println(Arrays.toString(chars));
//
//        chars = (char[]) innerChar.get(subStr);
//        System.out.println(Arrays.toString(chars));

        //Our main String
//        String mainString = "i_love_java";
//        //Substring holds value 'java'
//        String subString = mainString.substring(7);
//
//        System.out.println(mainString);
//        System.out.println(subString);
//
//        //Lets see what's inside mainString
//        Field innerCharArray = String.class.getDeclaredField("value");
//        innerCharArray.setAccessible(true);
//        char[] chars = (char[]) innerCharArray.get(mainString);
//        System.out.println(Arrays.toString(chars));
//
//        //Now peek inside subString
//        chars = (char[]) innerCharArray.get(subString);
//        System.out.println(Arrays.toString(chars));
//
        String s1 = "abcd5";
        String s2 = "abcd" + "5";
//        String s2 = new StringBuilder("abcd5").toString();
        String s3 = "abcd" + new String("5");  //heap 中产生两个对象，
        String s4 = "abcd" + s1.length();  // heap 中产生一个对象
        System.out.println(s3 == s4);

        System.out.println(s1 == s3);
        System.out.println(s1 == s2);
        System.out.println(s1 == s4);
        System.out.println(s2 == s4);
        System.out.println(s3.intern() == s4.intern());

        String a = "Hello World";
        String b = "Hello World";

        String res = a.substring(0, 4) + b.substring(6, 10);
        System.out.println(a == b);
        System.out.println(res == a);


//        String s1 = "abc";
//        String s2 = new String(s1);
//        System.out.println(s1 == s2);
//        String s3 = s2.intern();
//        System.out.println(s1 == s3);
//        System.out.println(new StringTest().equals(new StringTest()));
//        String a = "a";
//        String b = new String("a");
//        System.out.println(a.hashCode());
//        System.out.println(b.hashCode());
//        System.out.println(a == b);
//        System.out.println(a.equals(b));


    }
}
