package com.dmz.service.utils;

import java.io.*;

/**
 * Created by dmz on 2016/6/20.
 */
public class ByteCharFileUtil {

    public static void Byte2File(File file, InputStream stream) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file, true)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = stream.read(buffer, 0, 1024)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
    }
}
