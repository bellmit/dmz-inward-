package dmztest.UnicodeCharset;

/**
 * @author dmz
 * @date 2017/3/17
 */
/*
* Unicode 0x0000~0xFFFF   Basic
*         0x010000~0x10FFFF  supplementary
*/
public class UnicodeTest {
    static String newString(int codePoint) {

        return new String(Character.toChars(codePoint));
    }

    public static void main(String[] args) {

        char a = '梦';
        System.out.format("%x",(int)a);

        char high = '\uD800';
        char low = '\uDFC3';
        System.out.println(high);
        System.out.println(low);
        System.out.println(Character.isHighSurrogate(high));
        System.out.println(Character.isLowSurrogate(low));

        char bmp = '\u68a6';
        System.out.println("BMP:"+bmp);
        char[] chars = {'\uD800', '\uDFC3'};
        String supplementary = new String(chars);
        System.out.println("Supplementary:" + supplementary);





        int highCodePoint = Character.codePointAt(chars, 0);
        int lowCodePoint = Character.codePointAt(chars, 1);

        System.out.println(highCodePoint == high);
        System.out.println(lowCodePoint == low);
        int ss = Character.toCodePoint(high, low);
        System.out.println(newString(ss));

        int characterCount = Character.charCount(ss);
        System.out.println(characterCount);

//        int codePoint = 0x100001;
//        int count = Character.charCount(codePoint);
//        char[] chars = Character.toChars(codePoint);
//        System.out.println(count);
//        for (char c : chars) {
//            System.out.print(c);
//        }
//        for (int germanyChar = 0x10000;germanyChar<0x10FFF;germanyChar++) {
//            String temp = new String(Character.toChars(germanyChar));
//            if (temp.replaceAll("\\s*", "").length() == 0) {
//                continue;
//            }
//            System.out.println(germanyChar+"-->"+new String(Character.toChars(germanyChar)));
//        }
    }
}
