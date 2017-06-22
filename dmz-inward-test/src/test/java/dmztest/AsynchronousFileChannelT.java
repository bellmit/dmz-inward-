package dmztest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * Created by dmz on 2016/7/29.
 */
class NioAsynchonousRead implements CompletionHandler<Integer, AsynchronousFileChannel> {

    // need to keep track of the next position.
    int pos = 0;
    ByteBuffer buffer = null;

    public void completed(Integer result, AsynchronousFileChannel attachment) {
        // if result is -1 means nothing was read.
        if (result != -1) {
            pos += result;  // don't read the same text again.
            // your output command.
            System.out.println(new String(buffer.array()));

            buffer.clear();  // reset the buffer so you can read more.
        }
        // initiate another asynchronous read, with this.
        attachment.read(buffer, pos , attachment, this );


    }
    public void failed(Throwable exc,
                       AsynchronousFileChannel attachment) {
        System.err.println ("Error!");
        exc.printStackTrace();
    }

    public void doit() {
        Path file = Paths.get("a.file");
        AsynchronousFileChannel channel =  null;
        try {
            channel = AsynchronousFileChannel.open(file);
        } catch (IOException e) {
            System.err.println ("Could not open file: " + file.toString());
            System.exit(1); // yeah.  heh.
        }
        buffer = ByteBuffer.allocate(1000);

        // start off the asynch read.
        channel.read(buffer, pos , channel, this );
        // this method now exits, thread returns to main and waits for user input.
    }

}
public class AsynchronousFileChannelT {

    public static void main(String[] args) {
//        new NioAsynchonousRead().doit();
//        WriteFileAsynchronous();
        WriteFileAsynchronous2();
    }

    private static void WriteFileAsynchronous2() {
        Path path = Paths.get("aaaa.file");

        try {
            AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.CREATE,StandardOpenOption.WRITE);
            ByteBuffer buffer = ByteBuffer.allocate(100);
            buffer.put("You am 明".getBytes());
            buffer.flip();
            fileChannel.write(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    System.out.println("Bytes of write:" + result);
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    System.out.println("Faile:"+exc);
                }
            });
//            Future<Integer> feature = fileChannel.write(buffer, 0);
//            while (!feature.isDone()) {
//                System.out.println(feature.toString());
//            }
//            System.out.println("Write file done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void WriteFileAsynchronous() {

        Path path = Paths.get("aaaa.file");

        try {
            AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.CREATE,StandardOpenOption.WRITE);
            ByteBuffer buffer = ByteBuffer.allocate(100);
            buffer.put("I am 明".getBytes());
            buffer.flip();
            Future<Integer> feature = fileChannel.write(buffer, 0);
            while (!feature.isDone()) {
                System.out.println(feature.toString());
            }
            System.out.println("Write file done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void ReadFileAsynchronous() {
        Path path = Paths.get("a.file");
        try {
            AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
            ByteBuffer buffer = ByteBuffer.allocate(24);
            Future<Integer> feature = fileChannel.read(buffer, 0);
            while (!feature.isDone()) {
                System.out.println("Data reading...");
            }
            buffer.flip();

            byte[] bytes = new byte[buffer.limit()];

            buffer.get(bytes);

            System.out.println(new String(bytes));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
