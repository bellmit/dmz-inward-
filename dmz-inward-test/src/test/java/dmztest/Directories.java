package dmztest;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

/**
 * Created by dmz on 2016/7/27.
 */
public class Directories {
    public static void main(String[] args) {
//        ShowDirectories();
//        CreateDirectorie();
//        CreateDirectories();
//        CreateTempDirectory();
//        listDirectoryContents();
        listDirectoryContentsWithFilter();
    }

    private static void ShowDirectories() {
        Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
        for (Path name: dirs) {
            System.err.println(name);
        }
    }

    private static void CreateDirectorie() {
        Path path = Paths.get("B");
        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void CreateDirectories() {
        Path path = Paths.get("B/CC");
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void CreateTempDirectory() {
        try {
            Path path = Files.createTempDirectory("B");
            System.out.println(path);
            Files.delete(path);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listDirectoryContents() {
        Iterable<Path> dires = FileSystems.getDefault().getRootDirectories();
        for (Path path : dires) {
            try {
                DirectoryStream<Path> content = Files.newDirectoryStream(path);
                for (Path path_ : content) {
                    System.out.println(path_.getFileName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void listDirectoryContentsWithFilter() {
//        DirectoryStream.Filter<Path> filter = entry -> Files.isDirectory(entry);
        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {

            @Override
            public boolean accept(Path entry) throws IOException {
                return Files.isDirectory(entry);
            }
        };
        Iterable<Path> dires = FileSystems.getDefault().getRootDirectories();
        for (Path path : dires) {
            try {
//                DirectoryStream<Path> content = Files.newDirectoryStream(path,"*.*");
                DirectoryStream<Path> content= Files.newDirectoryStream(path, filter);
                for (Path path_ : content) {
                    System.out.println(path_.getFileName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
