package dmztest.ThreadExamples;

import java.util.concurrent.*;

/**
 * @author dmz
 * @date 2017/12/15
 */
public class ThreadCallbackRunableTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CallBack futureTask = new CallBack(() -> System.out.println("Running...."));
        executor.submit(futureTask);
        //try {
        //    String result = futureTask.get();
        //} catch (Exception ex ) {
        //    ex.printStackTrace();
        //}
        executor.shutdown();
    }
}

class CallBack extends FutureTask<String>{

    @Override
    protected void done() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Sleep 3 seconds");
    }

    public CallBack(Runnable runnable) {
        super(runnable, null);
    }
}

