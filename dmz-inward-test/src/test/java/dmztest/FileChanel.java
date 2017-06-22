package dmztest;

import org.apache.commons.lang3.CharEncoding;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by dmz on 2016/7/18.
 */
public class FileChanel {

    private static void RandomAccessFile() {
        Path file = Paths.get("c.file");

        String s = "I was here!\n";
        byte data[] = s.getBytes();
        ByteBuffer out = ByteBuffer.wrap(data);

        ByteBuffer copy = ByteBuffer.allocate(12);

        try (FileChannel fc = (FileChannel.open(file,StandardOpenOption.READ,StandardOpenOption.WRITE))) {
            // Read the first 12
            // bytes of the file.
            int nread;
            do {
                nread = fc.read(copy);
            } while (nread != -1 && copy.hasRemaining());

            // Write "I was here!" at the beginning of the file.
//            fc.position(0);
            while (out.hasRemaining())
                fc.write(out);
            out.rewind();

            // Move to the end of the file.  Copy the first 12 bytes to
            // the end of the file.  Then write "I was here!" again.
            long length = fc.size();
            fc.position(length-1);
            copy.flip();
            while (copy.hasRemaining())
                fc.write(copy);
            while (out.hasRemaining())
                fc.write(out);

            fc.force(true);

        } catch (IOException x) {
            System.out.println("I/O Exception: " + x);
        }
    }
    private static void TempFile() {
        try {
            Path file = Files.createTempFile(null, ".file");
            System.out.println(file.toAbsolutePath());
            Files.delete(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void ByteChannelIO() {
        Path p = Paths.get("a.file");
        try(SeekableByteChannel byteChannel = Files.newByteChannel(p, StandardOpenOption.READ);) {
            ByteBuffer buf = ByteBuffer.allocate(10);
            while ( true) {
                buf.clear();
                int read = byteChannel.read(buf);
                if (read == -1 ) {
                    break;
                }
                buf.flip();
                System.out.print(Charset.forName(CharEncoding.UTF_8).decode(buf));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void POSIXUNIXLog() {
        Path file = Paths.get("byteChanel.file");
        Set<OpenOption> options = new HashSet<>();
        options.add(StandardOpenOption.APPEND);
        options.add(StandardOpenOption.CREATE);
        Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-r-----");
        FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
        String str = "Hello 明天";
        byte[] bytes = str.getBytes(Charset.defaultCharset());
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        try (SeekableByteChannel sbc = Files.newByteChannel(file, options, attr);) {
            sbc.write(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
//        ByteChannelIO();
//        POSIXUNIXLog();
//        TempFile();
        RandomAccessFile();
    }
}
