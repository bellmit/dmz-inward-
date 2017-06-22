package dmztest.stringtest;

/**
 * @author dmz
 * @date 2017/2/20
 */
public class StringTest {
    public static void main(String[] args) {
        String s1 = "abcd5";
        String b1 = "abcd5";
        String s2 = "abcd" + "5";
        String s3 = "abcd" + new String("5");  //heap 中产生两个对象，
        String s4 = "abcd" + s1.length();  // heap 中产生一个对象
        System.out.println(s1 == s3);
        System.out.println(s1 == s4);
        System.out.println(s2 == s4);
        System.out.println(b1 == s1);
        System.out.println(s3.intern() == s4.intern());


        String a = "a";
        String b = "a";
        String c = new String("a").intern(); // two objects in memory,
        System.out.println(a == b);
        System.out.println(a == c);

        String aa = new String("aa");
        String bb = new String("aa");
        System.out.println(aa == bb);
    }
}
