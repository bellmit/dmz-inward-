package dmztest.ThreadUnCaughtExecption;

/**
 * Created by dmz on 2016/8/24.
 */
class ThreadException implements Runnable {

    @Override
    public void run() {
        Thread.currentThread().setUncaughtExceptionHandler(new ThreadExceptionCaught());  //优先级最高
        System.out.println(Integer.parseInt("123"));
        System.out.println(Integer.parseInt("456"));
        System.out.println(Integer.parseInt("Test"));
        System.out.println(Integer.parseInt("987"));
        System.out.println(Integer.parseInt("111"));

    }
}

class ThreadExceptionCaught implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("An exception has been captured\n");
        System.out.printf("Thread: %s\n", t.getId());
        System.out.printf("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
        System.out.printf("Stack Trace: \n");
        e.printStackTrace(System.out);
        System.out.printf("Thread status: %s\n", t.getState());
        new Thread(new ThreadException()).start();
    }
}

class ThreadExceptionCaught2 implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
//        System.out.printf("An exception has been captured\n");
//        System.out.printf("Thread: %s\n", t.getId());
//        System.out.printf("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
//        System.out.printf("Stack Trace: \n");
//        e.printStackTrace(System.out);
//        System.out.printf("Thread status: %s\n", t.getState());
        new Thread(new ThreadException()).start();
    }
}
public class CaughtThreadException {
    public static void main(String[] args) {
        Thread thread = new Thread(new ThreadException());
        thread.setUncaughtExceptionHandler(new ThreadExceptionCaught2()); //没有在线程内部设置捕获器的优先级高
        thread.start();
        System.out.println(thread.getState());
    }
}
