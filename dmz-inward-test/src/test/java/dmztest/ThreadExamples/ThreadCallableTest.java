package dmztest.ThreadExamples;

import java.util.concurrent.*;

/**
 * @author dmz
 * @date 2017/12/15
 */
public class ThreadCallableTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        ExecutorService executor = Executors.newCachedThreadPool();
        FutureTask<String> future = new FutureTask<>(new ThreadCallable());

        //new Thread(future).start();
        executor.submit(future);
        /* 同步结果，并且设置超时时间 */
        System.out.println(future.get(100000, TimeUnit.MILLISECONDS));
        System.out.println("finished");
    }
}
class ThreadCallable implements Callable<String> {

    public String call() throws Exception {
        //Thread.sleep(10000);
        int a = 10 / 0;
        return "callStatus=OK";
    }
}
