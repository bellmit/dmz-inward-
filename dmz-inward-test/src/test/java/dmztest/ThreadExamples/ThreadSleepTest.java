package dmztest.ThreadExamples;

import java.util.*;

/**
 * Created by dmz on 2016/8/15.
 */

public class ThreadSleepTest {
    public static void main(String[] args) {
//        Thread thread = new Thread(() -> {
//            try {
//                Thread.sleep(1000);
//                System.out.println("a");
//            } catch (InterruptedException e) {
//                System.out.println("Interupted...");            }
//
//        });
//        thread.start();
//        System.out.println("B");
//        thread.interrupt();
        int i = Integer.MIN_VALUE;
        System.out.println(i);
        i /= -1;
        System.out.println(i);

        Set set = new HashSet<>();
        set.add(new Object());

        Collections.synchronizedSet(set);
        Collections.synchronizedMap(new HashMap<>());

        Map<String, String> map = new HashMap<>();
        map.put("zhangjie", "1");
        map.put(new String("zhangjie"), "2");
        System.out.println(map.size());

        Map<String, String> imap = new IdentityHashMap<>();  //比较引用
        imap.put(new String("zhangjie"), "1");
        imap.put(new String("zhangjie"), "2");
        System.out.println(imap.size());
        System.out.println(new String("zhangjie") == "zhangjie");

    }
}
