package dmztest.stack;

/**
 * @author dmz
 * @date 2017/2/16
 */
public class StackLevel {

    private Long level = 1L;

//    private String a = "2";

    public void stackLevel() {
        level++;
//        a = a + "";
        stackLevel();
    }

    public static void main(String[] args) {

        StackLevel stackLevel = new StackLevel();
        try {
            stackLevel.stackLevel();
        } catch (StackOverflowError e) {
            System.out.println(stackLevel.level);
        }

    }
}
