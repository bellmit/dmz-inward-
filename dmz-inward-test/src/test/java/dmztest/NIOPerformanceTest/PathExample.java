package dmztest.NIOPerformanceTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author dmz
 * @date 2017/3/1
 */
public class PathExample {
    public static void main(String[] args) throws IOException {

//        CopyFiles("C:/Users/dmz/IdeaProjects/javaio/one.txt","C:/Users/dmz/IdeaProjects/javaio/copy.txt");
//         DeleteFiles("C:/Users/dmz/IdeaProjects/javaio/copy.txt");
//        MoveFiles("C:/Users/dmz/IdeaProjects/javaio/one.txt","C:/Users/dmz/IdeaProjects/javaio/dd");
        CheckFiles("C:/Users/dmz/IdeaProjects/javaio/one.txt");
//        isFileCanAccess("C:/Users/dmz/IdeaProjects/dmz-inward/dmz-inward-test/one.txt");

    }

    private static void CopyFiles(String source, String target) {
        /**
         * 可以拷贝文件夹,文件夹中的内容不会拷贝
         *
         * REPLACE_EXISTING 替换已经存在的文件(否则文件已经存在 则报错)
         *
         * 当复制一个符号链接，链接的目标被复制。如果你想复制链接本身而不是链接的内容，请指定的 NOFOLLOW_LINKS
         */
        try {
            Files.copy(Paths.get(source), Paths.get(target), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void DeleteFiles(String fileName) {
        /**
         * 删除文件或文件夹(删除的文件夹中不能不为空)
         *
         * 文件不存在　　报错
         * 文件夹不存在　不报错
         *
         * 若为软连接,则只删除软连接
         */
        try {
//            Files.delete(Paths.get(fileName));
            Files.deleteIfExists(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void MoveFiles(String source, String target) {
        /**
         *
         * REPLACE_EXISTING (目标文件存在则覆盖,不指定增报错)
         */
        try {
            Files.move(Paths.get(source), Paths.get(target), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void CheckFiles(String filesName) {
        System.out.println(Files.exists(Paths.get(filesName)));
    }

    private static void isFileCanAccess(String filesName) {
        /**
         * 检测文件是否可以访问
         */
        Path file = Paths.get(filesName);
        boolean isRegularExecutableFile = Files.isRegularFile(file) &
                Files.isReadable(file) & Files.isExecutable(file);
        System.out.println(isRegularExecutableFile);
//        Files.isWritable(file);
    }

}
