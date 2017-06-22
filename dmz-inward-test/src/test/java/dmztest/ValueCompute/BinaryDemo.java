package dmztest.ValueCompute;

/**
 * @author dmz
 * @date 2017/2/28
 */
public class BinaryDemo {

    public static void main(String[] args) {

        byte age = -1;
        System.out.println(age);
        System.out.format("十进制：%d \n", age);
        System.out.format("八进制：%o \n", age);
        System.out.format("十六进制：%x \n",age);
        System.out.format("二进制：" + Integer.toBinaryString(age & 0xFF)+"\n");
        System.out.format("十六进制:" + Integer.toHexString(age & 0xFF) + "\n");
        System.out.format("八进制：" + Integer.toOctalString(age & 0xFF) + "\n");


    }

}
