package dmztest.lock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author dmz
 * @date 2017/2/15
 */
class Document {

    private final List<String> lines;

    public Document(List<String> lines) {
        this.lines = lines;
    }

    public List<String> getLines() {
        return lines;
    }

    public static Document fromFile(File file) {
        List<String> lines = new LinkedList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

            String line = bufferedReader.readLine();
            while (line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Document(lines);
    }
}

class Folder {

    private final List<Folder> subFolder;
    private final List<Document> document;

    public List<Folder> getSubFolder() {
        return subFolder;
    }

    public List<Document> getDocument() {
        return document;
    }

    public Folder(List<Folder> folder, List<Document> document) {
        this.subFolder = folder;
        this.document = document;
    }

    public static Folder fromFolder(File dir) {

        LinkedList<Folder> subFolder = new LinkedList<>();
        LinkedList<Document> document = new LinkedList<>();

        for (File file : dir.listFiles()) {

            if (!file.canRead()) {
                continue;
            }

            if (file.isDirectory()) {
                subFolder.add(Folder.fromFolder(file));
            } else {
                document.add(Document.fromFile(file));
            }
        }
        return new Folder(subFolder, document);
    }
}

class CountWords {

    private static String[] wordCount(String line) {
        return line.trim().split("(\\s|\\p{Punct})+");
    }

    static Long count(Document document, String searchWord) {
        long count = 0;

        if (document != null) {
            for (String line : document.getLines()) {
                for (String word : wordCount(line)) {
                    if (searchWord.equalsIgnoreCase(word)) {
                        count += 1;
                    }
                }
            }
        }
        return count;
    }
}

class DocumentSearchTask extends RecursiveTask<Long> {

    private Document document;
    private String searchWord;

    public DocumentSearchTask(Document document, String searchWord) {
        this.document = document;
        this.searchWord = searchWord;
    }

    @Override
    protected Long compute() {
        return CountWords.count(document, searchWord);
    }

}

class FolderSearchTask extends RecursiveTask<Long> {

    private Folder folder;
    private String searchWord;

    public FolderSearchTask(Folder folder, String searchWord) {
        this.folder = folder;
        this.searchWord = searchWord;
    }

    @Override
    protected Long compute() {

        long count = 0;
        List<RecursiveTask<Long>> forks = new LinkedList<>();
        for (Folder folder:this.folder.getSubFolder()) {
            FolderSearchTask task = new FolderSearchTask(folder, searchWord);
            forks.add(task);
            task.fork();
        }
        for (Document document : folder.getDocument()) {
            DocumentSearchTask task = new DocumentSearchTask(document, searchWord);
            forks.add(task);
            task.fork();
        }
        for (RecursiveTask<Long> fork : forks) {
            count += fork.join();
        }
        return count;
    }
}
public class ComputeWords {

    private static ForkJoinPool forkJoinPool = new ForkJoinPool();

    private static long countWordParallel(Folder folder, String searchWord) {
        return forkJoinPool.invoke(new FolderSearchTask(folder, searchWord));
//        return new FolderSearchTask(folder, searchWord).compute();
    }
    public static void main(String[] args) {
        File file = new File("C:\\Users\\dmz\\IdeaProjects\\dmz-inward\\dockerdir\\sourcse");
        Folder folder = Folder.fromFolder(file);
        long startTime = System.currentTimeMillis();
        System.out.println(startTime);
        long count = countWordParallel(folder, "dmz");
        long elapse = System.currentTimeMillis() - startTime;
//        long count = 0;
//        for (Document document : folder.getDocument()) {
//            count += CountWords.count(document, "A");
//        }
        System.out.println(count);
    }
}
