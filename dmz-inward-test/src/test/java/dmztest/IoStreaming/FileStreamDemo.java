package dmztest.IoStreaming;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author dmz
 * @date 2017/2/28
 */
public class FileStreamDemo {
    public static void main(String[] args) throws IOException {

        String fileName = "C:/Users/dmz/IdeaProjects/dmz-inward/dmz-inward-test/one.txt";
        String fileNameNew = "C:/Users/dmz/IdeaProjects/dmz-inward/dmz-inward-test/oneTwoone.txt";
//        ByteBuffer buffer = ByteBuffer.allocate(1024*1024*100);

        FileInputStream fileInputStream = new FileInputStream(new File("C:/Users/dmz/IdeaProjects/dmz-inward/dmz-inward-test/one.txt"));
        FileDescriptor fd = fileInputStream.getFD();
        fd.sync();

        FileChannel outChannel = new FileOutputStream(new File(fileNameNew)).getChannel();
        outChannel.write(ByteBuffer.allocate(1), 9); //创建固定大小的文件

//        RandomAccessFile randomAccessFile = new RandomAccessFile(fileNameNew, "rw");
//        randomAccessFile.setLength(100); //创建固定大小的文件

//        finInput.write("明".getBytes());

        FileChannel channelIn = new FileInputStream(new File(fileName)).getChannel();
        System.out.println("Read Before:" + channelIn.size());
        MappedByteBuffer mapByteBuffer = channelIn.map(FileChannel.MapMode.READ_ONLY, 0, channelIn.size());

        byte[] bytes = new byte[1024];

        for (int offset=0;offset<channelIn.size();offset+=1024) {
            if (channelIn.size() - offset < 1024) {
                mapByteBuffer.get(new byte[(int)(channelIn.size() - offset)]);
            } else {
                mapByteBuffer.get(bytes);
            }
        }

        System.out.println("Read After:" + channelIn.size());
        System.out.println(new File(fileName).length());

//            ByteToChar(fileName);
//            CharToByte(fileName);
//            readByte(fileName);
//            writeByte(fileName);
//            readChar(fileName);
//            writeChar(fileName);
//            readBufferByte(fileName);
//            writeBufferByte(fileName);
//            readBufferChar(fileName);
//            writeBufferChar(fileName);

    }

    private static void readBufferByte(String fileName) {
        /**
         *Buffer Bytes , read将从buffer中读取
         */
        try (InputStream inputStream = new FileInputStream(fileName);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 1024)) {

            int read;
            while (-1 != (read = bufferedInputStream.read())) {
                System.out.println((char) read);
            }

            System.out.println("---------------");

        } catch (Exception e) {

        }
    }

    private static void writeBufferByte(String fileName) {
        /**
         * Buffer Bytes,write将buffer写入,流关闭之前会flush(可以手动flush)
         */
        try (OutputStream ot = new FileOutputStream(fileName);
             BufferedOutputStream outputStream = new BufferedOutputStream(ot, 1024)) {

            byte b1 = (byte) -26;
            byte b2 = (byte) -104;
            byte b3 = (byte) -114;
            outputStream.write(b1);
            outputStream.write(b2);
            outputStream.write(b3);

            outputStream.flush();

        } catch (Exception e) {

        }
    }

    private static void readBufferChar(String fileName) {
        /**
         * Buffer chars,读取Buffer中的char
         */
        try (Reader reader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(reader, 1024)) {

//            int read;
//
//            while(-1!=(read = bufferedReader.read())){
//                System.out.println((char) read);
//            }

            System.out.println("---------------");

            System.out.println(bufferedReader.readLine());

        } catch (Exception e) {

        }
    }

    private static void writeBufferChar(String fileName) {

        /**
         * Buffer Chars,write将buffer写入
         */

        try (Writer writer = new FileWriter(fileName);
             BufferedWriter bufferedWriter = new BufferedWriter(writer);
             PrintWriter printWriter = new PrintWriter(writer, true)) {

            char c1 = '明';
            char c2 = '梦';
            bufferedWriter.write(c1);
            bufferedWriter.flush();


            /**
             * PrintWriter printWriter = new PrintWriter(writer,true)
             * printWriter.print, println, format等都将刷新输出流
             */
            printWriter.format("|Hello %c|", c2);
            printWriter.write(c2);
            printWriter.println("|love dmz");
            printWriter.println("||");

            //            printWriter.flush();

        } catch (Exception e) {

        }
    }

    private static void writeChar(String fileName) {
        /**
         *FileWriter 采用file.encoding进行 编码
         */
        try (Writer writer = new FileWriter(fileName)) {

            String name = "明";

            writer.write(name);

        } catch (Exception e) {

        }
    }

    private static void readChar(String fileName) {
        /**
         * FileReader 采用 系统默认字符集 （file.encoding）进行 解码
         */
        try (Reader reader = new FileReader(fileName)) {

            int read;

            while (-1 != (read = reader.read())) {
                System.out.println((char) read);
            }

            System.out.println("---------------");

        } catch (Exception e) {

        }
    }

    private static void writeByte(String fileName) {
        /**
         * Write Bytes
         */
        try (OutputStream outputStream = new FileOutputStream(fileName)) {
            byte b1 = (byte) 195;
            byte b2 = (byte) 247;

            outputStream.write(b1);
            outputStream.write(b2);

        } catch (Exception e) {

        }
    }

    private static void readByte(String fileName) {
        /**
         * Read Bytes
         */
        try (InputStream inputStream = new FileInputStream(fileName)) {

            int read;

            while (-1 != (read = inputStream.read())) {
//                System.out.println(read & 0xff);
                System.out.println(Integer.toHexString(read & 0xFF));
            }

            System.out.println("---------------");

        } catch (Exception e) {

        }
    }

    private static void ByteToChar(String fileName) {
        /**
         * Transfer Byte to Char
         */
        try (InputStream inputStream = new FileInputStream(fileName);
             InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8")) {

            int read;

            while (-1 != (read = reader.read())) {
                System.out.println((char) read);
            }

            System.out.println("---------------");

        } catch (Exception e) {

        }
    }

    private static void CharToByte(String fileName) {
        /**
         * Transfer Char to Byte
         */
        try (OutputStream outputStream = new FileOutputStream(fileName);
             OutputStreamWriter writer = new OutputStreamWriter(outputStream, "GBK")) {

            String name = "明";

            writer.write(name);

        } catch (Exception e) {

        }

    }
}

