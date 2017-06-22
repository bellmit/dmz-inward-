package dmztest;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * Created by dmz on 2016/7/29.
 */
public class LineNumberStreamT {
    public static void main(String[] args) throws IOException {
        LineNumberReader lineNumberReader =
                new LineNumberReader(new FileReader("aaaa.file"));

        int data = lineNumberReader.read();
        while(data != -1){
            char dataChar = (char) data;
            System.out.println(dataChar);
            data = lineNumberReader.read();
            int lineNumber = lineNumberReader.getLineNumber();
            System.out.println(lineNumber);
        }
        lineNumberReader.close();
    }

}
