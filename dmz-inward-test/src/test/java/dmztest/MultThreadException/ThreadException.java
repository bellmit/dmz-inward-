package dmztest.MultThreadException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadException implements Runnable,Thread.UncaughtExceptionHandler{
    @Override
    public void run() {
        for (int i=0;i<2;i++) {
            Thread t = Thread.currentThread();
            System.out.println("run() by "+t);
            System.out.println("eh = "+t.getUncaughtExceptionHandler());
            System.out.println(i);
            if (i == 0) {
                throw new RuntimeException();
            }
        }
    }
    //现象：控制台打印出异常信息，并运行一段时间后才停止
    public static void main(String[] args){
        //就算把线程的执行语句放到try-catch块中也无济于事
        try{
            Thread.setDefaultUncaughtExceptionHandler(new ThreadException());
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new ThreadException());
        }catch(RuntimeException e){
            System.out.println("Exception has been handled!");
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught  "+e);
    }
}