package dmztest;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

/**
 * Created by dmz on 2016/7/12.
 */
public class CharsetTest {
    private static final Logger LOGGER = Logger.getLogger(CharsetTest.class);

    public static void main(String args[]) throws CharacterCodingException, InterruptedException, UnsupportedEncodingException {


        String str = new String("联");
        byte[] bytes = str.getBytes("GBK");
        for (byte b : bytes) {
            System.out.format("%X == " + Integer.toBinaryString(b & 0xFF)+"\n", b);
        }
        //  00000000 0110 1010

        // 006A


        short yu = (short) '￡';
        System.out.println(yu < 0);
        System.out.println((char) 0x006A);

        char cM = 65505;
        System.out.println(cM);
        char[] toChar = str.toCharArray();
        Charset charset = Charset.forName("UTF-8");
        ByteBuffer byteBuffer = charset.encode(CharBuffer.wrap(str.toCharArray()));
        System.out.println(str.getBytes("UTF-8").length);
        System.out.println(str.getBytes("UTF-16").length);


//        str.getBytes();
//
//        int a = '鱼';
//        System.out.println((char) 0x00); //16
//        System.out.println(020); //8
//        System.out.println(0b1_1); //2
//
//        System.out.println(Integer.toBinaryString(a & 0xFFFF));
//        System.out.println(a);
//        System.out.println(String.format("%x", a));
//
//        for (int i = 0; i < 10; i++) {
//            LOGGER.error("Info log [" + i + "].");
//            Thread.sleep(500);
//        }
////        SortedMap<String, Charset> map = Charset.availableCharsets();
////        for (String key : map.keySet()) {
////            System.out.println(map.get(key));
////        }
////        ByteBuffer buffer = null;
////        Charset gbk = Charset. forName( "GBK");
////        gbk.newDecoder().decode(buffer);
////        gbk.decode(buffer);
//        System.out.println(Charset.isSupported(CharEncoding.UTF_8));
//        Charset charset = Charset.forName(CharEncoding.UTF_8);
//        System.out.println(charset.name());
//        Set<String> alias = charset.aliases();
//        for (String alia : alias) {
//            System.out.println(alia);
//        }
    }
}
