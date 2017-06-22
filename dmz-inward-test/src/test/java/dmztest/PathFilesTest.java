package dmztest;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.Properties;
import java.util.Set;

/**
 * Created by dmz on 2016/7/14.
 */
public class PathFilesTest {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.home"));
//        Properties pro = System.getProperties();
//        for (Object o : pro.keySet()) {
//            System.out.println(o.toString()+":"+pro.get(o).toString());
//        }
//        Path outPath = Paths.get("C:\\Users\\dmz\\IdeaProjects\\openapi-client-java\\src\\main\\java\\net\\frontnode\\openapi\\outfile.txt");
//        Path outPaths = FileSystems.getDefault().getPath("C:\\Users\\dmz\\IdeaProjects\\openapi-client-java\\src\\main\\java\\net\\frontnode\\openapi\\outfile.txt\"");
//        Path pa = Paths.get(System.getProperty("user.home"), "logs", "foo.log");
//        Pathget(outPath);
//        PathCreate();
//        CheckFile();
//        DeleteFile();
//        Copy();
//        Move();
//        Metadata();
        FileStore();
    }

    private static void FileStore() {
        //          FileStore
        System.out.format("%-20s %12s %12s %12s %12s\n", "Filesystem", "kbytes", "used", "avail" , "unallocate");
        final long K = 1024;
        FileSystem fs = FileSystems.getDefault();
        for (FileStore store: fs.getFileStores()) {
            try {
                long total = store.getTotalSpace() / K;
                long used = (store.getTotalSpace() - store.getUnallocatedSpace()) / K;
                long avail = store.getUsableSpace() / K;

                String s = store.toString();
                if (s.length() > 20) {
                    System.out.println(s);
                    s = "";
                }
                System.out.format("%-20s %12d %12d %12d\n", s, total, used, avail);
                FileStore fileStore = Files.getFileStore(Paths.get("a.file"));
                long ftotal = fileStore.getTotalSpace() / K;
                long fused = (fileStore.getTotalSpace() - fileStore.getUnallocatedSpace()) / K;
                long favail = fileStore.getUsableSpace() / K;
                long funallocate = fileStore.getUnallocatedSpace() / K;
                System.out.format("%-20s %12d %12d %12d %12d\n", fileStore.toString(), ftotal, fused, favail,funallocate);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static void Metadata() {
//        Metadata means data about data
        Path p = Paths.get("a.file");
        Path news = Paths.get("new.file");
        try {
            System.out.format("size of file :%d\n", Files.size(p));
            System.out.format("is directory :%s\n", Files.isDirectory(p));
            System.out.format("is regular file :%s\n", Files.isRegularFile(p));
            System.out.format("is SymbolicLink :%s\n", Files.isSymbolicLink(p));
            System.out.format("is Hidden :%s\n", Files.isHidden(p));
            System.out.format("last modify time:%s\n", Files.getLastModifiedTime(p));
            FileTime fileTime = FileTime.fromMillis(System.currentTimeMillis());
            Files.setLastModifiedTime(p, fileTime);
            System.out.format("owner of file:%s\n", Files.getOwner(p, LinkOption.NOFOLLOW_LINKS));
            BasicFileAttributes attr = Files.readAttributes(p, DosFileAttributes.class);
            System.out.println(JSON.toJSONString(attr));
//            Unix or Linux Support
//            System.out.format("permissons of file:%s\n", Files.getPosixFilePermissions(p,LinkOption.NOFOLLOW_LINKS));
//            System.out.format("attributes of file:%s\n", Files.getAttribute(p,"unix:uid"));
//            For linux
            PosixFileAttributes attrs = Files.readAttributes(p, PosixFileAttributes.class);
            Set<PosixFilePermission> permissions = attrs.permissions();
//            设置文件权限
//            Set<PosixFilePermission> permissions_String = PosixFilePermissions.fromString("rw-------");
//            FileAttribute<Set<PosixFilePermission>> ap = PosixFilePermissions.asFileAttribute(permissions_String);
            FileAttribute<Set<PosixFilePermission>> ap = PosixFilePermissions.asFileAttribute(permissions);
            Files.createFile(news, ap);
//          设置文件拥有者,所属组
            UserPrincipal owner = p.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName("dmz");
            GroupPrincipal group = p.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByGroupName("DMZ");
            Files.getFileAttributeView(p, PosixFileAttributeView.class).setGroup(group);
            Files.setOwner(p, owner);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void Move() {
        Path ap = Paths.get("bb");
        Path aap = Paths.get("C");
        try {
//            不能移到有内容的文件夹
            Files.move(ap, aap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void Copy() {
        Path ap = Paths.get("a.file");
        Path cp = Paths.get("c.file");
        Path adp = Paths.get("a");
        Path cdp = Paths.get("c");
        try {
            Files.copy(adp, cdp, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
            Files.copy(ap, cp, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void DeleteFile() {
        Path p = Paths.get("aa.file");
        try {
            Files.delete(p);
            Files.deleteIfExists(p);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void CheckFile() {
//        Note that !Files.exists(path) is not equivalent to Files.notExists(path).
//        When you are testing a file's existence, three results are possible:
//
//        The file is verified to exist.
//        The file is verified to not exist.
//        The file's status is unknown. This result can occur when the program does not have access to the file.
//        If both exists and notExists return false, the existence of the file cannot be verified.
        Path p = Paths.get("a.file");
        System.out.println(Files.exists(p));
        System.out.println(Files.notExists(p));
        System.out.println(Files.isExecutable(p));

//        Checking File Accessibility
        boolean isRegularExecutableFile = Files.isRegularFile(p) &
                Files.isReadable(p) & Files.isExecutable(p);
        System.out.println(isRegularExecutableFile);
    }

    private static void PathCreate() {
        Path p1 = Paths.get("a.file");
        Path p2 = Paths.get("b.file");
        System.out.println(p1.toAbsolutePath());
        Path p1_p2 = p1.relativize(p2);
        System.out.println(p1.resolve(p1_p2));
        try {
            System.out.println(p1_p2);
            System.out.println(p1.resolve(p1_p2).toRealPath());
            System.out.println(p1.resolve(p1_p2).toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }


        Path p11 = Paths.get("home");
        Path p33 = Paths.get("home/sally/bar");
        Path p11_to_p33 = p11.relativize(p33);
        System.out.println(p11_to_p33.toAbsolutePath());
        Path p33_to_p11 = p33.relativize(p11);
        try {
            System.out.println(p33_to_p11.toAbsolutePath().toRealPath(LinkOption.NOFOLLOW_LINKS));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Path path = Paths.get("C:/home/./music/users.txt");
        Path np = path.normalize(); //Remove . .. in the middle .
        try {
            System.out.println(path.toRealPath(LinkOption.NOFOLLOW_LINKS));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Path firstPath = Paths.get("/home/music/users.txt");
        Path secondPath = Paths.get("/docs/status.txt");

        System.out.println("From firstPath to secondPath: " + firstPath.relativize(secondPath));
        System.out.println("From secondPath to firstPath: " + secondPath.relativize(firstPath));

        Path aa = Paths.get("a.file");
        Path bb = Paths.get("./a.file");
        try {
            System.out.println(aa.equals(bb));
            System.out.println(Files.isSameFile(aa, bb));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void Pathget(Path path) {
        System.out.format("toString: %s%n", path.toString());
        System.out.format("getFileName: %s%n", path.getFileName());
        System.out.format("getName(0): %s%n", path.getName(0));
        System.out.format("getNameCount: %d%n", path.getNameCount());
        System.out.format("subpath(0,2): %s%n", path.subpath(0, 2));
        System.out.format("getParent: %s%n", path.getParent());
        System.out.format("getRoot: %s%n", path.getRoot());
        System.out.format("URI:%s%n", path.toUri());
        Path p = Paths.get("a.file");
        System.out.println(p.toAbsolutePath());
        System.out.format("Resolve:%s%n", path.resolve("outfile.txt"));
//        If true is passed to this method and the file system supports symbolic links, this method resolves any symbolic links in the path.
//        If the Path is relative, it returns an absolute path.
//        If the Path contains any redundant elements, it returns a path with those elements removed.
        try {
            System.out.println(p.toRealPath());
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
        } catch (IOException e) {

        }

    }
}
