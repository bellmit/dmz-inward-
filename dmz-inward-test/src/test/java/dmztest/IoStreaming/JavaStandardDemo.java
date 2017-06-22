package dmztest.IoStreaming;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * @author dmz
 * @date 2017/2/28
 */
public class JavaStandardDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {

        String name = "明";
        byte[] bys = name.getBytes(); // file.encoding编码 encode

        for (byte by : bys) {
//            System.out.println((char) (by & 0xff));
//            System.out.println(Integer.toHexString(by & 0xFF));
            System.out.format("%010X \n",by & 0xff); // X大写16进制 ， x小写16进制
        }
        System.out.println("--------------------");
        bys = name.getBytes("GBK");
        String nStr = new String(bys, "GBK"); //decode乱码
        System.out.println(nStr);
//        System.out.println("-------------------");
//        StandardErrorStreamOut("DMZ IS DMZ.");
//        StandardStreamOut("dmz is dmz.");
//        System.out.println("-------------------");
//        StandardStreamIn();
//        System.out.println("-------------------");
        StandardStreamInPacking();


    }

    private static void StandardStreamOut(String name) {
        /**
         * 标准输出
         * 内部的字节流转为字符流
         */
        System.out.println(name);
        PrintStream print = System.out;
        print.println(name); //BufferWrite textOut , textOut.write(name)

    }

    private static void StandardErrorStreamOut(String name) {
        /**
         * 标准错误输出
         * 内部的字节流转为字符流
         * 相比标准输出，标准错误优先打印
         */
        System.err.println(name);
    }

    private static void StandardStreamInPacking() {
        /**
         * 包装字节流
         */

        try {

            InputStreamReader reader = new InputStreamReader(System.in, "UTF-8");

            int str = reader.read();

            System.out.println((char) str);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void StandardStreamIn() {
        /**
         * 标准输入，字节流
         */
        try {
            int str = System.in.read();
            System.out.println((char) str);
            System.out.println("----------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
