package dmztest.IoStreaming;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author dmz
 * @date 2017/2/28
 */
public class ScannerDemo {
    public static void main(String[] args) {
        List<String> words = new LinkedList<>();
        Scanner scanner = new Scanner("A  BC,,$,TOM..  T");
        scanner.useDelimiter("[^a-zA-Z]+|\\p{Punct}+");
//        scanner.useDelimiter("[^a-zA-Z]");
        while (scanner.hasNext()) {
            String word = scanner.next();
            words.add(word);
//            System.out.print(scanner.next());
        }
        for (String str : words) {
            System.out.println(str);
        }
        System.out.println(words.size());
    }
}
