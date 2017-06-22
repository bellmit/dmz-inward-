package dmztest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by dmz on 2016/7/29.
 */
public class PrintStreamT {
    public static void main(String[] args) throws FileNotFoundException {
        OutputStream outputStream = new FileOutputStream("a.file");
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        System.out.println("H明");
    }
}
