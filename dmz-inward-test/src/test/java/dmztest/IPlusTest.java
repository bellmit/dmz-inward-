package dmztest;

/**
 * Created by dmz on 2016/8/31.
 */
public class IPlusTest {
    public static void main(String[] args) {
//        int i = 5;
//        double d = 4.5 + i;
//        i += 4.5;
//        System.out.println(d);
//        System.out.println(i);
        String str = "wasdefrgtgyhujikolazsxdcfvgbz";
        String tr = str.replaceAll("(.{10})", "$1\n");
        System.out.print(tr);
//        int i = 0;
//        int length = str.length();
//        while (i < length) {
//            int begin = i;
//            i += 10;
//            if (i > str.length()) {
//                i = str.length();
//            }
//            String temp = str.substring(begin, i)+"\n";
//            System.out.print(temp);
//
//        }
    }
}
