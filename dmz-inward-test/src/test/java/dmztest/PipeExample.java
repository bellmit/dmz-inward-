package dmztest;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by dmz on 2016/7/29.
 */
public class PipeExample {
    public static void main(String args[]) throws IOException {
        final PipedInputStream inputStream = new PipedInputStream();
        final PipedOutputStream outputStream = new PipedOutputStream();
        inputStream.connect(outputStream);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    outputStream.write("HelloWorld".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int data = inputStream.read();
                    while (data != -1) {
                        System.out.print((char) data);
                        data = inputStream.read();
                    }
                } catch (IOException e) {
                }
            }
        });
        thread2.start();
        thread1.start();

    }
}
