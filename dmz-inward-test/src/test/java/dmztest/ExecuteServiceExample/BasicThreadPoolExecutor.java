package dmztest.ExecuteServiceExample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by dmz on 2016/8/24.
 */
class CallAbleFeture implements Callable<Integer> {

    private Integer number;

    public CallAbleFeture(Integer number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int result = 1;
        if (number == 0 || number == 1 || number == null) {
            return result;
        }
        for (int i = 1; i <= number; i++) {
            result *= i;
        }
        return result;
    }
}

public class BasicThreadPoolExecutor {
    public static void main(String[] args) {

//        BasicnewCachedThreadPool();
        List<Future<Integer>> list = new ArrayList<>();
        Random random = new Random();
        ThreadPoolExecutor execute = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            int next = random.nextInt(10);
            Future<Integer> future = execute.submit(new CallAbleFeture(next));
            System.out.println("Right Now IsDone " + future.isDone());
            list.add(future);
        }

        for (Future<Integer> future : list) {
            try {
                System.out.println("Future result is - " + " - " + future.get() + "; And Task done is " + future.isDone());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        execute.shutdown();

    }

    private static void BasicnewCachedThreadPool() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            Task task1 = new Task("Task " + i);
            System.out.println("Task " + i + " added");
            executor.execute(task1);
        }

        executor.setRejectedExecutionHandler((r, ex) -> {
            System.out.println("Rejected Excution...");
        });

        executor.shutdown();
        executor.execute(new Task(""));
    }

}
