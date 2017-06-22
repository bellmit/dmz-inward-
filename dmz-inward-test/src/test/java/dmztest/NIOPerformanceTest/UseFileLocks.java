package dmztest.NIOPerformanceTest;// $Id$

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class UseFileLocks {
    static private final int start = 10;
    static private final int end = 20;

    private static void lockFile() throws Exception {
        // Get file channel
        RandomAccessFile raf = new RandomAccessFile("C:/Users/dmz/IdeaProjects/dmz-inward/dmz-inward-test/one.txt", "rw");
        long size = raf.length();
        FileChannel fc = raf.getChannel();

        // Get lock
        System.out.println("trying to get lock");
        FileLock lock = fc.tryLock(0,1,true);
        System.out.println("isShared Lock ?:"+lock.isShared());
        System.out.println("got lock!");

        // Pause
        System.out.println("pausing");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ie) {
        }

        // Release lock
        System.out.println("going to release lock");
        lock.release();
        System.out.println("released lock");

        raf.close();
    }

    static public void main(String args[]) throws Exception {
        new Thread() {
            @Override
            public void run() {
                try {
                    lockFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                try {
                    lockFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
