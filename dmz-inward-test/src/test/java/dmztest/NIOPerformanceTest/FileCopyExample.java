package dmztest.NIOPerformanceTest;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;

/**
 * Created by dmz on 2016/8/31.
 */
public class FileCopyExample {
    public static void main(String[] args) {
//        CopyBytes(); //10~15s 缓冲池大小决定copy速度,用户内存
//        CopyBytesNIOwithIO(); //类似CopyBytes
//        ChannelToChannel(); // 40~55s  效率高,最差
//        MappedBufferCopy(); //45s , 读快,写慢
//        CopyBytesWithDirectMemory(); //20~35s 缓冲池大小决定copy速度,虚拟内存大小 , 适合用于超大文件
//        FilesToFiles(); //10~15s 速度最优,建议使用
    }

    private static void CopyBytesNIOwithIO() {
        Path inPath = Paths.get("C:\\\\【6v电影www.dy131.com】透明人BD中英双字1024高清.rmvb");
        Path outPath = Paths.get("C:\\K\\a.rmvb");
        try (FileChannel inChannel = FileChannel.open(inPath, StandardOpenOption.READ);
             BufferedOutputStream bufferedOut = new BufferedOutputStream(Files.newOutputStream(outPath));) {
            ByteBuffer buffer = ByteBuffer.allocate(1024*1024*100);
            byte[] bytes = new byte[1024*1024*100];
            long start = System.currentTimeMillis();
            while (-1 != (inChannel.read(buffer))) {
                buffer.flip();
                if (bytes.length > (buffer.limit() - buffer.position())) {
                    byte[] bytes1 = new byte[buffer.limit()-buffer.position()];
                    buffer.get(bytes1);
                    bufferedOut.write(bytes1);
                } else {
                    buffer.get(bytes);
                    bufferedOut.write(bytes);
                }
//                outChannel.write(buffer);
                buffer.clear();
            }
            long end = System.currentTimeMillis();
            System.out.println("Copy File With CopyBytes Times:" + (end - start) / 1000 + "秒");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void MappedBufferCopy() {
        Path inPath = Paths.get("C:\\\\【6v电影www.dy131.com】透明人BD中英双字1024高清.rmvb");
        Path outPath = Paths.get("C:\\K\\a.rmvb");
        try (FileChannel channelIn = FileChannel.open(inPath, StandardOpenOption.READ);
             FileChannel channelOut = FileChannel.open(outPath, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
//            long fileLength = channelIn.size();
            long start = System.currentTimeMillis();
            MappedByteBuffer mapByteBuffer = channelIn.map(FileChannel.MapMode.READ_ONLY, 0, channelIn.size());


            System.out.println("Read Done!");

            int size = channelOut.write(mapByteBuffer);
            long end = System.currentTimeMillis();

            System.out.println("Write Size:" + size);
            System.out.println("ChannelIn Size:" + channelIn.size());
            System.out.println("Copy File With CopyBytes Times:" + (end - start) / 1000 + "秒");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void CopyBytes() {
        Path inPath = Paths.get("C:\\\\【6v电影www.dy131.com】透明人BD中英双字1024高清.rmvb");
        Path outPath = Paths.get("C:\\K\\a.rmvb");
        try (FileChannel inChannel = FileChannel.open(inPath, StandardOpenOption.READ);
             FileChannel outChannel = FileChannel.open(outPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024*1024*100); //100MB
            long start = System.currentTimeMillis();
            while (-1 != (inChannel.read(buffer))) {
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }
            long end = System.currentTimeMillis();
            System.out.println("Copy File With CopyBytes Times:" + (end - start) / 1000 + "秒");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void CopyBytesWithDirectMemory() {
        Path inPath = Paths.get("C:\\\\【6v电影www.dy131.com】透明人BD中英双字1024高清.rmvb");
        Path outPath = Paths.get("C:\\K\\a.rmvb");
        try (FileChannel inChannel = FileChannel.open(inPath, StandardOpenOption.READ);
             FileChannel outChannel = FileChannel.open(outPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024*1024*800);
            long start = System.currentTimeMillis();
            while (-1 != (inChannel.read(buffer))) {
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }
            long end = System.currentTimeMillis();
            System.out.println("Copy File With CopyBytes With DirectMemory Times:" + (end - start) / 1000 + "秒");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void FilesToFiles() {
        Path inPath = Paths.get("C:\\\\【6v电影www.dy131.com】透明人BD中英双字1024高清.rmvb");
        boolean isRegularExecutableFile = Files.isRegularFile(inPath) &
                Files.isReadable(inPath) & Files.isExecutable(inPath);
        if (!isRegularExecutableFile) {
            return;
        }
        Path outPath = Paths.get("C:\\K\\a.rmvb");
        try {
            long start = System.currentTimeMillis();
            Files.copy(inPath, outPath, StandardCopyOption.REPLACE_EXISTING);
            long end = System.currentTimeMillis();
            System.out.println("Copy File With Files Times:" + (end - start) / 1000 + "秒");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void ChannelToChannel() {
        Path inPath = Paths.get("C:\\\\【6v电影www.dy131.com】透明人BD中英双字1024高清.rmvb");
        boolean isRegularExecutableFile = Files.isRegularFile(inPath) &
                Files.isReadable(inPath) & Files.isExecutable(inPath);
        if (!isRegularExecutableFile) {
            return;
        }
        Path outPath = Paths.get("C:\\K\\a.rmvb");
        try (FileChannel inChannel = FileChannel.open(inPath, StandardOpenOption.READ);
             FileChannel outChannel = FileChannel.open(outPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.READ)) {
            long start = System.currentTimeMillis();
            inChannel.transferTo(0, inChannel.size(), outChannel);
            long end = System.currentTimeMillis();
            System.out.println("Copy File With FileChannel Times:" + (end - start) / 1000 + "秒");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
