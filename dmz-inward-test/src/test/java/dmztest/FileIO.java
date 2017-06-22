package dmztest;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.CharEncoding;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Created by dmz on 2016/7/15.
 */
public class FileIO {
    public static void main(String[] args) {
//        SmallFilesIO();
        NIO_With_IO();
    }

    private static void NIO_With_IO() {
//      InputStreamReader Files.newInputStream()
        Charset charset = Charset.forName(CharEncoding.UTF_8);
        Path p = Paths.get("a.file");
        try (InputStream inputStream = Files.newInputStream(p);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            reader.close();

            JSON.toJSONString(new Object());
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        OutputStreamWriter Files.newOutStream()
        String str = "梦DMZ";
        Charset encode = Charset.forName(CharEncoding.UTF_8);
        byte[] bytes = str.getBytes(encode);
        try (OutputStream outputStream = Files.newOutputStream(p, StandardOpenOption.APPEND);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream)) {
            bufferedOutputStream.write(bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void SmallFilesIO() {
        Path p = Paths.get("a.file");
        try {
//      return bytes
            byte[] bytes = Files.readAllBytes(p);
            for (byte b : bytes) {
                System.out.println(b);
            }
//      return string
            List<String> str = Files.readAllLines(p, Charset.forName(CharEncoding.UTF_8));
            for (String s : str) {
                System.out.println(s);
            }
//      write all bytes
            Path pb = Paths.get("abytes.file");
            Files.write(pb, bytes);
//       write all String
            Path ps = Paths.get("astring.file");
            Files.write(ps, str, Charset.forName(CharEncoding.UTF_8), StandardOpenOption.APPEND);
//      return bufferedreader
            Charset set = Charset.forName(CharEncoding.UTF_8);
            try (BufferedReader reader = Files.newBufferedReader(p, set)) {
                String line;
                System.out.println("-------------------------------");
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException x) {
                System.err.format("IOException %s\n", x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//      return bufferedwriter
        Path wrierP = Paths.get("writer.file");
        Charset sett = Charset.forName(CharEncoding.UTF_8);
        String lines = "Hello,DMZ , 明";
        try (BufferedWriter writer = Files.newBufferedWriter(wrierP, sett)) {
            writer.write(lines, 0, lines.length());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
