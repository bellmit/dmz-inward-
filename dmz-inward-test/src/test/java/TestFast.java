/**
 * @author dmz
 * @date 2017/2/27
 */
public class TestFast {
    public static void selectSort(int[] numbers) {
        int size = numbers.length; //数组长度
        int temp; //中间变量

        for (int i = 0; i < size; i++) {
            int k = i;   //待确定的位置
            //选择出应该在第i个位置的数
            for (int j = i + 1; j < size; j++) {
                if (numbers[j] < numbers[k]) {
                    k = j;
                }
            }
            //交换两个数
//            temp = numbers[i];
//            numbers[i] = numbers[k];
//            numbers[k] = temp;
            if (i == k) {
                continue;
            }
            numbers[i] ^= numbers[k];
            numbers[k] ^= numbers[i];
            numbers[i] ^= numbers[k];

        }
    }

    public static void bubbleSort(int[] numbers) {
        int temp = 0;
        int size = numbers.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1])  //交换两数位置
                {
                    numbers[j] ^= numbers[j + 1];
                    numbers[j + 1] ^= numbers[j];
                    numbers[j] ^= numbers[j + 1];
                }
            }
        }
    }

    public static void main(String[] args) {

        int[] a = new int[10];
        int i;
        for (i=1;i<=10;i++) {
            a[i] = 0;
        }
        return;
        //System.out.println(NetworkUtils.getHostName());
        //System.out.println(NetworkUtils.getSiteIp());
        //Integer aa = null;
        //System.out.println(String.valueOf(aa));
        //BigDecimal saleRate = new BigDecimal("1");
        //System.out.println(saleRate.divide(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP));
//        System.out.println(System.getProperty("line.separator").length());
//        System.out.println("V" + (1 + 1) + 0.32);
//
//        String hexString = "abC";
//        char[] chars = hexString.toCharArray();
//
//        byte[] result = new byte[hexString.length() / 2];
//        char[] charArray = hexString.toCharArray();
//
//        for (int i = 0, c = 0; i < charArray.length; i += 2, c++) {
//            result[c] = (byte) (Integer.parseInt(new String(charArray, i, 2), 16));
//        }
//        System.out.println(new String(charArray, 0, 2));
//        System.out.println(Integer.parseInt(new String(charArray, 0, 2), 16));

//        int[] array = {3, 3, 3, 1, 5, 7, 3, 6, 0};
////        bubbleSort(array);
//        selectSort(array);
//        for (int i = 0; i < array.length; i++) {
//            System.out.print(array[i] + " ");
//        }
//        System.out.println();
//        int temp;
//        for (int i = 0; i < array.length; i++) {
//            int k = i;
//
//            for (int j = array.length -1 ; j > i ; j--) {
//                if (array[j] < array[k]) {
//                    k = j;
//                }
//            }
////            temp = array[i];
////            array[i] = array[k];
////            array[k] = temp;
////            A=A^B;
////            B=B^A;
////            A=A^B;
//            array[i] ^= array[k];
//            array[k] ^= array[i];
//            array[i] ^= array[k];
//
//
//        }
//        for (int i = 0; i < array.length; i++) {
//            System.out.print(array[i] + " ");
//        }
//        System.out.println();
//
//        int a = 10;
//        int b = 10;
//        System.out.println(a + " " + b);
//
//        a ^= a;  //不能异或自己 result = 0
//        a ^= a;
//        a ^= a;
//        System.out.println(a + " " + a);

    }
}
