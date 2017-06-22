package dmztest;

import java.util.Random;

/**
 * @author dmz
 * @date 2016/12/29
 */
public class RandomTest {

    private static Random random = new Random();
    public static void main(String[] args) {
        System.out.println(random.nextInt(3));
    }
}
