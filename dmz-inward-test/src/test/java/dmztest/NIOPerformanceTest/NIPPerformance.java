package dmztest.NIOPerformanceTest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.management.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

/**
 * Created by dmz on 2016/8/30.
 */
public class NIPPerformance {
    public static void main(String[] args) throws IOException {
        MemoryMXBean memorymbean = ManagementFactory.getMemoryMXBean();
        MemoryUsage usage = memorymbean.getHeapMemoryUsage();
        System.out.println("INIT HEAP: " + usage.getInit() / 1024 / 1024 + "MB");
        System.out.println("MAX HEAP: " + usage.getMax() / 1024 / 1024 + "MB");
        System.out.println("USE HEAP: " + usage.getUsed() / 1024 / 1024 + "MB");
        System.out.println("\nFull Information:");
        System.out.println("Heap Memory Usage: "
                + memorymbean.getHeapMemoryUsage());
        System.out.println("Non-Heap Memory Usage: "
                + memorymbean.getNonHeapMemoryUsage());
        List<String> inputArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
        System.out.println("===================java options=============== ");
        System.out.println(inputArguments);



        System.out.println("=======================通过java来获取相关系统状态============================ ");
        int i = (int)Runtime.getRuntime().totalMemory()/1024;//Java 虚拟机中的内存总量,以字节为单位
        System.out.println("总的内存量 i is "+i);
        int j = (int)Runtime.getRuntime().freeMemory()/1024;//Java 虚拟机中的空闲内存量
        System.out.println("空闲内存量 j is "+j);
        System.out.println("最大内存量 is "+Runtime.getRuntime().maxMemory()/1024);

        System.out.println("=======================OperatingSystemMXBean============================ ");
        OperatingSystemMXBean osm = ManagementFactory.getOperatingSystemMXBean();
//    System.out.println(osm.getFreeSwapSpaceSize()/1024);
//    System.out.println(osm.getFreePhysicalMemorySize()/1024);
//    System.out.println(osm.getTotalPhysicalMemorySize()/1024);

        //获取操作系统相关信息
        System.out.println("osm.getArch() "+osm.getArch());
        System.out.println("osm.getAvailableProcessors() "+osm.getAvailableProcessors());
        //System.out.println("osm.getCommittedVirtualMemorySize() "+osm.getCommittedVirtualMemorySize());
        System.out.println("osm.getName() "+osm.getName());
        //System.out.println("osm.getProcessCpuTime() "+osm.getProcessCpuTime());
        System.out.println("osm.getVersion() "+osm.getVersion());
        //获取整个虚拟机内存使用情况
        System.out.println("=======================MemoryMXBean============================ ");
        MemoryMXBean mm=ManagementFactory.getMemoryMXBean();
        System.out.println("getHeapMemoryUsage "+mm.getHeapMemoryUsage());
        System.out.println("getNonHeapMemoryUsage "+mm.getNonHeapMemoryUsage());
        //获取各个线程的各种状态，CPU 占用情况，以及整个系统中的线程状况
        System.out.println("=======================ThreadMXBean============================ ");
        ThreadMXBean tm=ManagementFactory.getThreadMXBean();
        System.out.println("getThreadCount "+tm.getThreadCount());
        System.out.println("getPeakThreadCount "+tm.getPeakThreadCount());
        System.out.println("getCurrentThreadCpuTime "+tm.getCurrentThreadCpuTime());
        System.out.println("getDaemonThreadCount "+tm.getDaemonThreadCount());
        System.out.println("getCurrentThreadUserTime "+tm.getCurrentThreadUserTime());

        //当前编译器情况
        System.out.println("=======================CompilationMXBean============================ ");
        CompilationMXBean gm=ManagementFactory.getCompilationMXBean();
        System.out.println("getName "+gm.getName());
        System.out.println("getTotalCompilationTime "+gm.getTotalCompilationTime());

        //获取多个内存池的使用情况
        System.out.println("=======================MemoryPoolMXBean============================ ");
        List<MemoryPoolMXBean> mpmList=ManagementFactory.getMemoryPoolMXBeans();
        for(MemoryPoolMXBean mpm:mpmList){
            System.out.println("getUsage "+mpm.getUsage());
            System.out.println("getMemoryManagerNames "+mpm.getMemoryManagerNames().toString());
        }
        //获取GC的次数以及花费时间之类的信息
        System.out.println("=======================MemoryPoolMXBean============================ ");
        List<GarbageCollectorMXBean> gcmList=ManagementFactory.getGarbageCollectorMXBeans();
        for(GarbageCollectorMXBean gcm:gcmList){
            System.out.println("getName "+gcm.getName());
            System.out.println("getMemoryPoolNames "+gcm.getMemoryPoolNames());
        }
        //获取运行时信息
        System.out.println("=======================RuntimeMXBean============================ ");
        RuntimeMXBean rmb=ManagementFactory.getRuntimeMXBean();
        System.out.println("getClassPath "+rmb.getClassPath());
        System.out.println("getLibraryPath "+rmb.getLibraryPath());
        System.out.println("getVmVersion "+rmb.getVmVersion());


//        noBufferReadFileWithNIO(); //2~3s 速度取决buffer size
//        noBufferReadFileWithIO(); //2~3s 速度取决buffer size
//        noBufferReadFileDirect();//2s  速度取决于设置的虚拟内存大小,适用于超大文件的读取.
//        bufferReadFile(); // ~4s 速度很不错,一般文件的使用方式
//        mappedBufferReadFile(); //3s 适用于超大文件的读取.小文件的效率却不是很高

    }

    private static void mappedBufferReadFile() throws IOException {
        File file = new File("C:\\\\【6v电影www.dy131.com】透明人BD中英双字1024高清.rmvb");
        FileInputStream fileIn = new FileInputStream(file);
        FileChannel channel = fileIn.getChannel();
        long length = file.length();
        MappedByteBuffer mappedBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
        long start = System.currentTimeMillis();

//        while (mappedBuffer.hasRemaining()) {
//            mappedBuffer.get();
//        }
        byte[] buffer = new byte[8192];
        for (int offset=0;offset<channel.size();offset+=8192) {
            if (length - offset > 8192) {
                mappedBuffer.get(buffer);
            } else {
                mappedBuffer.get(new byte[(int) (length-offset)]);
//                System.out.println("Boom !");
//                mappedBuffer.get(buffer);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("No Buffer Read File With MappedBuffer Times:" + (end - start) / 1000 + "秒");
    }

    private static void noBufferReadFileDirect() throws IOException {
        FileInputStream fileIn = new FileInputStream("C:\\\\【6v电影www.dy131.com】透明人BD中英双字1024高清.rmvb");
        FileChannel channel = fileIn.getChannel();
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024 * 800);  //最大值为默认的JVM Max Heap,可以由-XX:MaxDirectMemorySize=1024M，来设置allocateDirect的最大值
        // allocateDirect使用直接缓冲区

        long start = System.currentTimeMillis();

        while (-1 != (channel.read(buffer))) {
            buffer.flip();
            buffer.clear();
        }

        long end = System.currentTimeMillis();
        System.out.println("No Buffer Read File With Direct Times:" + (end - start) / 1000 + "秒");
    }

    private static void noBufferReadFileWithIO() throws IOException {
        File file = new File("C:\\\\【6v电影www.dy131.com】透明人BD中英双字1024高清.rmvb");
        FileInputStream fileIn = new FileInputStream(file);
        byte[] buffer = new byte[8192];
        long length = file.length();
        long start = System.currentTimeMillis();
//        System.out.println("length:" + file.length());
//        for (int offset=0 ; offset< file.length() ; offset+=1024) {
//            if (length - offset > 1024) {
//                int read = fileIn.read(buffer);
//                System.out.println(read);
//                if (read==-1) break;
//            } else {
//                System.out.println("done.");
//                int read = fileIn.read(new byte[(int) (length - offset)]);
//                System.out.println(read);
//                if (read==-1) break;
//            }
//        }
        while (true) {
            int read = fileIn.read(buffer);
            if (read == -1)
                break;
        }

        long end = System.currentTimeMillis();
        System.out.println("No Buffer Read File Times:" + (end - start) / 1000 + "秒");
    }

    private static void bufferReadFile() throws IOException {
        FileInputStream fileIn = new FileInputStream("C:\\\\【6v电影www.dy131.com】透明人BD中英双字1024高清.rmvb");
        BufferedInputStream bufferFileIn = new BufferedInputStream(fileIn);
        long start = System.currentTimeMillis();
        byte[] buffer = new byte[1024];
        while (-1 != (bufferFileIn.read(buffer))) {

        }
        long end = System.currentTimeMillis();
        System.out.println("Buffer Read File Times:" + (end - start) / 1000 + "秒");
    }

    private static void noBufferReadFileWithNIO() throws IOException {
        FileInputStream fileIn = new FileInputStream("C:\\\\【6v电影www.dy131.com】透明人BD中英双字1024高清.rmvb");
        FileChannel channel = fileIn.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(8192);
        long start = System.currentTimeMillis();
        while (true) {
            buffer.clear();
            int read = channel.read(buffer);
            if (read == -1)
                break;
            buffer.flip();
        }
        long end = System.currentTimeMillis();
        System.out.println("No Buffer Read File With NIO Times:" + (end - start) / 1000 + "秒");
    }
}
