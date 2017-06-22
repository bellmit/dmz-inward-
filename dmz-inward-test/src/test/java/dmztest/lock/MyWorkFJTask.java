package dmztest.lock;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author dmz
 * @date 2017/2/15
 */

abstract class WorkFJTask extends ForkJoinTask<Void> {

    public String getName() {
        return name;
    }

    private String name;

    public WorkFJTask(String name) {
        this.name = name;
    }

    public abstract void compute();

    @Override
    public Void getRawResult() {
        return null;
    }

    @Override
    protected void setRawResult(Void value) {

    }

    @Override
    protected boolean exec() {
        Long start = System.currentTimeMillis();
        compute();
        Long diff = System.currentTimeMillis() - start;
//        System.out.println(String.format("MyWorkTask:s% : d% Milliseconds complete\n", name, diff));
        System.out.println("MyWordkTask:" + name + ", Milliseconds:" + diff);
        return true;
    }

}

class Task extends WorkFJTask {

    private int[] array;
    private int start;
    private int end;

    public Task(String name, int[] array, int start, int end) {
        super(name);
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    public void compute() {
        if (end - start > 100) {
            int mid = (start + end) / 2;
            Task task1 = new Task(this.getName() + " " + Thread.currentThread().getName(), array, start, mid);
            Task task2 = new Task(this.getName() + " " + Thread.currentThread().getName(), array, mid, end);
            invokeAll(task1, task2);
        } else {
            for (int i = start; i < end; i++) {
                array[i]++;
            }
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

public class MyWorkFJTask {

    public static void main(String[] args) {
        int[] array = new int[1000];
        ForkJoinPool joinPool = new ForkJoinPool();
        Task task = new Task("Task", array, 0, array.length);
        joinPool.invoke(task);
        System.out.println("================");
        joinPool.shutdown();
    }
}
