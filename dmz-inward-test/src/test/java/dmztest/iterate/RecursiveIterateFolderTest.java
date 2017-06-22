package dmz.iterate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author dmz
 * @date 2017/2/22
 */
class Document {

    private String docName;
    private List<String> docContent;

    public Document(String docName, List<String> docContent) {
        this.docContent = docContent;
        this.docName = docName;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public List<String> getDocContent() {
        return docContent;
    }

    public void setDocContent(List<String> docContent) {
        this.docContent = docContent;
    }
}

class Folder {

    private static LinkedList<File> tempFolder = new LinkedList<>();

    public Folder() {

    }

    public Folder(String folderName, List<Folder> folders, List<Document> docs) {
        this.folderName = folderName;
        this.folders = folders;
        this.docs = docs;
    }

    private String folderName;
    private List<Folder> folders = new ArrayList<>();
    private List<Document> docs = new ArrayList<>();

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    // 非遞歸方式
    public static Folder fromFolderWithOutRecursive(File dir) {

        List<Folder> folders = new ArrayList<>();
        List<Document> docs = new ArrayList<>();
        tempFolder.add(dir);

        while (!tempFolder.isEmpty()) {
            for (File file : tempFolder.removeFirst().listFiles()) {
                if (file.isDirectory()) {
                    System.out.println("folder:" + file.getName());
                    tempFolder.add(file);
                } else {
                    System.out.println("file:" + file.getName());
                    docs.add(fromFile(file));
                }
            }
        }


        return new Folder(dir.getName(), folders, docs);
    }

    // 遞歸會導致堆棧溢出
    public static Folder fromFolder(File dir) {

        List<Folder> folders = new ArrayList<>();
        List<Document> docs = new ArrayList<>();

        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                System.out.println("folder:" + file.getName());
                folders.add(fromFolder(file));

            } else {
                System.out.println("file:" + file.getName());
                docs.add(fromFile(file));
            }
        }

        return new Folder(dir.getName(), folders, docs);
    }

    private static Document fromFile(File file) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Document(file.getName(), lines);
    }

    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    }

    public List<Document> getDocs() {
        return docs;
    }

    public void setDocs(List<Document> docs) {
        this.docs = docs;
    }
}

public class RecursiveIterateFolderTest {

    public static void main(String[] args) {

        File file = new File("C:\\Users\\dmz\\IdeaProjects\\dmz-inward\\dockerdir\\sourcse");
//        Folder folder = Folder.fromFolder(file);
//        Folder folder2 = Folder.fromFolderWithOutRecursive(file);

        String regular = "ABCDEFGHIJKMNLVDSDADSV";
        System.out.println(regular.replaceAll("(.{10})", "$1\n"));

        Matcher te = Pattern.compile("(.{10})").matcher(regular);
//        System.out.println(te.replaceAll("$1\n"));
    }
}
