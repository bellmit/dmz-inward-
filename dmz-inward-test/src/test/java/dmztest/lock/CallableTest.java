package dmztest.lock;

import java.util.concurrent.*;

/**
 * @author dmz
 * @date 2017/2/13
 */
public class CallableTest {
    private static class CallableAndFuture implements Callable<String> {

        public CallableAndFuture(int flag) {
            this.flag = flag;
        }
        private int flag = 0;

        @Override
        public String call() throws Exception {

            if (flag == 0) {
                System.out.println("Call:0");
                return "flag=" + flag;
            }
            if (flag == 1) {
                try {
                    while (true) {
                        System.out.println("looping.");
                        Thread.sleep(2000);
                    }

                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
                return "flag=" + flag;

            } else {
                System.out.println("Call:3");
                throw new Exception("Bad flag value!");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CallableAndFuture call1 = new CallableAndFuture(0);
        CallableAndFuture call2 = new CallableAndFuture(1);
        CallableAndFuture call3 = new CallableAndFuture(2);

        executor.submit(call1);
        Future<String> future2 = executor.submit(call2);
//        future2.get();
        executor.submit(call3);
        executor.shutdown();

        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
