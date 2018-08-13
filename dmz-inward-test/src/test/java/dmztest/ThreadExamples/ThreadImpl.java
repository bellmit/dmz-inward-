package dmztest.ThreadExamples;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author dmz
 * @date 2018/8/7
 */

class ThreadWithCallable implements Callable<String> {

  @Override
  public String call() {
    return "Thread Callable";
  }

}


class ThreadRunnable implements Runnable {

  private String name;

  public ThreadRunnable(String name) {
    this.name = name;
  }

  @Override
  public void run() {
    System.out.println("I'm " + name + ", coming from runnable");

  }

}

class ThreadExtend extends Thread {

  public ThreadExtend() {
    super();
  }

  public ThreadExtend(String name) {
    super(name);
  }

  @Override
  public void run() {
    System.out.println("I'm " + getName() + ", coming from extend");
  }

}

public class ThreadImpl {

  public static void main(String[] args) {

    BigDecimal perShareSum = new BigDecimal("00.99111");
    perShareSum = perShareSum.divide(new BigDecimal(1000)).setScale(4, RoundingMode.HALF_UP);
    System.out.println(perShareSum);

//    new ThreadExtend("ThreadExtend").start();
//    new Thread(new ThreadRunnable("ThreadRunnable")).start();
    ThreadWithCallable threadWithCallable = new ThreadWithCallable();
    FutureTask<String> futureTask = new FutureTask(threadWithCallable);
    new Thread(futureTask).start();
    try {
      System.out.println(futureTask.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

  }
}
