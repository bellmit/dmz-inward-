package JobExame;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author dmz
 * @date 2017/3/5
 */
public class Demo1 {
    public static void main(String[] args) {

        HashMap hashMap = new HashMap();
        Hashtable hashTable = new Hashtable();
        hashMap.put("index", "");
        System.out.println(null==null);
//        hashMap.put("index", "1");
        TreeMap treeMap = new TreeMap();
        TreeSet treeSet = new TreeSet();
        System.out.println(hashMap.size());
        System.out.println(hashMap.get(null));

//        HashSet set = new HashSet<>();
//        set.add("");
//        set.add("");
//        set.add("");
//        System.out.println(set.size());
//        ArrayList<String> list = new ArrayList<>(); //JDK1.8直接擴充
//
////        Collections.synchronizedList(list);
//        Vector vector = new Vector();
//        list.add("");
//
//        Thread t= new Thread(){
//            @Override
//            public void run() {
//                pong();
//            }
//        };
//        t.run();
//        System.out.print("ping");
//    }
//    static void pong() {
//        System.out.print("pong");
//    }
    }
}
