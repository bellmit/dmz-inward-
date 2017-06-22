package dmztest.IoStreaming;

import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dmz
 * @date 2017/2/26
 */
public class MultilThreadFileCopy {
    public static String SRC_FILE = "E:/跳马运动分析.MP4";
    public static String DES_FILE = "F:/跳马运动分析.MP4";
    public static int BYTE_ARRAY_SIZE = 1024 * 1024;
    public static final int THREAD_COUNT = 4;

    public static void main(String[] args){
        new MultilThreadFileCopy().testRandomCopy();
    }
    public void testRandomCopy(){
        System.out.println("RandomCopy 开始复制！");
        long start = System.nanoTime();

        long fileLength = 0;
        try( // 首先创建一个大小为和原文件一样大的 空文件
             RandomAccessFile srcFile = new RandomAccessFile(SRC_FILE, "r");
             RandomAccessFile desFile = new RandomAccessFile(DES_FILE, "rw");){
            fileLength = srcFile.length();
            desFile.setLength(fileLength);
        }catch (Exception e) {
            e.printStackTrace();
        }

        CountDownLatch finishSignal = new CountDownLatch(THREAD_COUNT); // 记录所有子线程的完成状态
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
        long copyLength = fileLength / THREAD_COUNT; // 每次复制的文件长度
        int i;
        for (i = 0; i < THREAD_COUNT - 1; i++){
            threadPool.execute(new RandomCopy(""+i,SRC_FILE, DES_FILE, i * copyLength, copyLength, finishSignal));
        }
        // 最后一个线程复制复制文件的剩部分，起始位置为i * copyLength，复制长度为fileLength - i*copyLength
        threadPool.execute(new RandomCopy(""+i,SRC_FILE, DES_FILE, i * copyLength, fileLength - i*copyLength, finishSignal));
        threadPool.shutdown(); // 关闭线程池入口

        try {
            finishSignal.await(); // 阻塞，直到 THREAD_COUNT 个线程全部完成才向下继续执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("RandomCopy用时：" + (System.nanoTime()-start)/Math.pow(10, 9));
    }
    class RandomCopy implements Runnable{
        private String name; // 线程名字
        private String src; // 源文件路径
        private String des; // 目标文件路径
        private long offset; // 复制的起始位置
        private long copyLength; // 当前线程负责复制的长度
        private CountDownLatch finishSignal; // 本线程结束的标识
        public RandomCopy (String name,String src, String des, long offset, long copyLength, CountDownLatch finishSignal){
            this(name, src, des, offset, copyLength);
            this.finishSignal = finishSignal;
        }
        public RandomCopy(String name,String src, String des, long offset, long copyLength){
            this.name = name;
            this.src = src;
            this.des = des;
            this.offset = offset;
            this.copyLength = copyLength;
        }
        @Override
        public void run() {
            System.out.println(name + " 开启");
            try( RandomAccessFile srcFile = new RandomAccessFile(src, "r");
                 RandomAccessFile desFile = new RandomAccessFile(des, "rw")){
                byte[] b = new byte[BYTE_ARRAY_SIZE];
                long hasCopyed = 0;
                int readOnce = 0;
                srcFile.seek(offset); // 找到开始复制的位置
                desFile.seek(offset); // 找到开始写的位置

                while( hasCopyed < copyLength ){
                    readOnce = srcFile.read(b);
                    if (readOnce < 0){
                        break;
                    }
                    desFile.write(b, 0, readOnce);
                    hasCopyed += readOnce;
                }
                System.out.println(name +" 执行完毕！");
            }catch (Exception e) {
                e.printStackTrace();
            }
            finishSignal.countDown(); // 通知当前线程已结束
        }
    }

}
