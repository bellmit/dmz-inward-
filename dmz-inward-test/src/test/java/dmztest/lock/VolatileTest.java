package dmztest.lock;

/**
 * @author dmz
 * @date 2017/2/17
 */
class VolatileWhile {

    private  boolean spin = true;

    public void spinStart() {
        while (spin) {
            System.out.println("spin...");
        }
    }

    public void spinEnd() {
        endSpin();
    }

    private void endSpin() {
        boolean  a = false;
        spin = a;
    }
}

public class VolatileTest {
    public static void main(String[] args) throws InterruptedException {

        VolatileWhile vw = new VolatileWhile();
        Thread t_one = new Thread(() -> {
            vw.spinStart();
        });
        Thread t2 = new Thread(() -> {
            vw.spinStart();
        });
        Thread t3 = new Thread(() -> {
            vw.spinStart();
        });
        Thread t4 = new Thread(() -> {
            vw.spinStart();
        });
        t_one.start();
//        t2.start();
//        t3.start();
//        t4.start();
        Thread.sleep(1000);
//        System.out.println("============");
        Thread t_two = new Thread(() -> {
            vw.spinEnd();
        });
        t_two.start();
    }
}
