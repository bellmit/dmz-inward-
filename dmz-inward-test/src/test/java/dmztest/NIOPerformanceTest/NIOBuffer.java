package dmztest.NIOPerformanceTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.channels.FileChannel;

/**
 * @author dmz
 * @date 2017/3/1
 */
public class NIOBuffer {
    public static void main(String[] args) {
        String source = "C:/Users/dmz/IdeaProjects/javaio/newio.txt" ;
        String target = "C:/Users/dmz/IdeaProjects/javaio/writesomebytes.txt" ;

        FileReadAndShow(source);
//        SliceBuffer();
//        TypesInByteBuffer();
//        UserFloatBuffer();
//        FileWriteSomeBytes(target);
//        FileLock(source);
    }

    private static void UserFloatBuffer() {
        /**
         * FloatBuffer
         */
        FloatBuffer buffer = FloatBuffer. allocate( 10);
        for ( int i = 0 ; i < buffer.capacity(); ++i) {
            float f = (float) Math. sin(((( float) i) / 10 ) * (2 * Math. PI));
            buffer.put(f) ;
        }

        buffer.flip();

        while (buffer.hasRemaining()) {
            float f = buffer.get();
            System. out.println(f);
        }
    }

    private static void TypesInByteBuffer() {
        /**
         * TypesInByteBuffer
         */
        ByteBuffer buffer = ByteBuffer. allocate( 100);

        buffer.putLong( 7000000000000000L);
        buffer.putDouble(Math. PI);
        buffer.put(( byte) 10 );
        buffer.putInt( 30);

        buffer.flip() ;

        System. out.println(buffer.getLong());
        System. out.println(buffer.getDouble());
        System. out.println(buffer.get());
        System. out.println(buffer.getInt());
    }

    private static void SliceBuffer() {
        /**
         * Slice ByteBuffer
         */
        ByteBuffer buffer = ByteBuffer. allocate( 10);

        for ( int i = 0 ; i < buffer.capacity(); i++) {
            buffer.put((byte ) i);
        }

        buffer.position(3 );
        buffer.limit( 7);

        ByteBuffer slice = buffer.slice() ;
        for ( int i = 0 ; i < slice.capacity(); i++) {
            byte a = slice.get(i);
            slice.put(i , (byte ) (a * 10)) ;
        }

        buffer.position(0 );
        buffer.limit(buffer.capacity()) ;

        while (buffer.remaining() > 0) {
            System.out.println(buffer.get()) ;
        }


    }

    private static void FileWriteSomeBytes(String fileName) {
        /**
         * NIO write file
         */
        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {

            FileChannel fout = outputStream.getChannel();

            byte[] messages = { 83, 111 , 109, 101, 32 , 98, 121, 116 , 101, 115, 46 };

//            ByteBuffer buffer = ByteBuffer.allocate(1024);
//            for (byte b:messages){
//                buffer.put(b);
//            }
//            buffer.flip();

            ByteBuffer buffer = ByteBuffer. wrap(messages) ;
            buffer.position( 0);
            buffer.limit(buffer.capacity()) ;

            fout.write(buffer) ;
        } catch (Exception e) {

        }
    }

    private static void FileLock(String fileName) {
        /**
         * File Lock
         */
        try (RandomAccessFile inputStream = new RandomAccessFile(fileName, "rw")) {
            FileChannel fc = inputStream.getChannel();
//            FileLock lock = fc.lock(1, 2, false);
            ByteBuffer buffer = ByteBuffer. allocate( 1024);

            fc.read(buffer) ;
            buffer.flip() ;
            while (buffer.remaining() > 0) {
                System.out.println(( char) buffer.get());
            }
            //            lock.release();
        } catch (Exception e) {

        }
    }

    private static void FileReadAndShow(String fileName) {
        /**
         * NIO read file
         */
        try (FileInputStream inputStream = new FileInputStream(fileName)) {
            FileChannel fin = inputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer. allocate( 1024);
            fin.read(byteBuffer) ;
            byteBuffer.flip() ;
//            Charset utf = Charset.forName("UTF-8");
//            CharBuffer charBuffer = utf.decode(byteBuffer);
//            System.out.println(charBuffer);
            int i = 0;
            while (byteBuffer.remaining() > 0) {
                byte b = byteBuffer.get() ;
                System.out.println( "Character " + i + ": " + ((char ) b));
                i++;
            }

        } catch (Exception e) {

        }

    }
}
