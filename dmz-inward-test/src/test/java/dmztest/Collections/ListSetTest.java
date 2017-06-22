package dmztest.Collections;

import java.util.*;

/**
 * Created by dmz on 2016/7/29.
 */
class People{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String name;
    private int age;
}
public class ListSetTest {
    public static void main(String[] args) throws InterruptedException {

        List<Student> list = new LinkedList<>();
        List<Integer> il = new ArrayList<>();

        Set<Student> sets = new TreeSet<>();
        sets.add(new Student("a", 20));
        sets.add(new Student("b", 10));
//        String name = "DMZ";
//        List<String> list = new ArrayList<>();
//        boolean add1 = list.add(name);    //true
//        boolean add2 = list.add(name);  //true elements can equal
//        System.out.println(JSON.toJSONString(list));
//        boolean remove1 = list.remove(name);    //true
//        boolean remove2 = list.remove("dmz"); //false
//        System.out.println(JSON.toJSONString(list));
//        System.out.println("-------------------------------------------");
//        Set<String> set = new HashSet<>();
//        boolean add1Set = set.add(name); // true
//        boolean add2Set = set.add(name); //false
//        boolean remove1Set = set.remove("dmz"); // false
//        boolean remove2Set = set.remove(name);  //true
//        System.out.println(JSON.toJSONString(set));
//        System.out.println("-------------------------------------------");
//          Set<Object> set = new HashSet<>();
//          Object o1 = new Object();
//          Object o2 = new Object();
//          boolean add1 = set.add(o1);
//          boolean add2 = set.add(o2);
//          System.out.println(JSON.toJSONString(set));
//        boolean addAll = set.addAll(list); // add the elements of ArrayList ,but not contains arraylist self
//        System.out.println(JSON.toJSONString(set));
//        Set<String> set = new HashSet<>();
//        list.add("dmzs");
//        set.add("dmz");
//        set.add("DMZ");
//        boolean re = set.removeAll(list); // remove the elements of list in set
//        boolean ret = set.retainAll(list);  // remove all elements except the elements of list , if list's element can not be found ,then set will be empty
//        System.out.println(JSON.toJSONString(set));
//        List<String> list2 = new ArrayList<>();
//        list2.add(name);
//        boolean contains = list.containsAll(list);
//        System.out.println(contains);

//        List<String> list = new ArrayList<>();
//        list.add("HelloWorld");
//
//        Iterator<String> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            String temp = iterator.next();
//            System.out.println(temp);
//        }

//        List list = new ArrayList();
//        list.add(null);
//        list.add(null);
//        System.out.println(JSON.toJSONString(list));
//        Set<Integer> set = new HashSet<>();
//        set.add(200);
//        set.add(201);
//        set.add(202);


//        List<Integer> list = new ArrayList<>();
//        list.add(200);
//        list.add(201);
//        list.add(202);
//        list.add(200);
//        list.add(2001);
//        list.add(2011);
//        list.add(2021);
//        list.add(2001);

//        List<String> str = new ArrayList<>();
//        str.add("dmz");
//        str.add("ym");
//        str.add("DMZ");
//        str.add("dmz");
//        Set<Integer> linkList = new LinkedHashSet<>(list);
//        Set<Integer> linkList = new HashSet<>(list);
//        System.out.println(list);
//        System.out.println(linkList);
//        List<Integer> list2 = new ArrayList<>();
//        list2.add(200);
//        list2.add(201);
//        list2.add(202);
//        System.out.println(list2.equals(list));
//        Stream<Integer> stream = set.stream();
//        stream.filter(item->item>200).forEach(item -> System.out.println(item));
//        List<Integer> temp = stream.filter(items -> items > 202).collect(Collectors.toList());
//        System.out.println(JSON.toJSONString(temp));
//        Integer[] array = set.toArray(new Integer[0]);


//        Set<Integer> setts = Arrays.asList(arrays).stream().collect(Collectors.toSet());
//        List<String> listArray = new ArrayList<>();
//        listArray.add("dmz");
//        listArray.add("DMZ");
//        listArray.add("YM");
//        listArray.add("DMZ");
////        listArray.remove("DMZ");
////        listArray.remove(Collectors.sin);
//        listArray.removeAll(Collections.singleton("One"));
//        System.out.println(listArray);












//
////      数组转为指定Set
//        Integer[] arrays = {1, 2, 3, 4};
////      Set<Integer> setts = Arrays.asList(arrays).stream().collect(Collectors.toSet()); //转入HashSet
//        Set<Integer> linkedHashSet = Arrays.asList(arrays).stream().collect(Collectors.toCollection(LinkedHashSet::new));
//
////        集合元素中的部分字段指定到set
//        List<People> peoples = new ArrayList<>();
//        peoples.listIterator();
//        peoples.iterator();
//        Set<String> hashSet = peoples.stream().map(People::getName).collect(Collectors.toSet());
//
////        删除指定元素
//        listArray.remove("dmz"); //删除一个dmz元素
//        listArray.removeAll(Collections.singleton("dmz"));  //删除所有dmz元素
//
////        转为array
//        Object[] as = listArray.toArray();
//        String[] ass = listArray.toArray(new String[0]);
//
//        Set<String> setss = new LinkedHashSet<>();
//        new TreeSet<>();
//        setss.add(null);
//        System.out.println(setss);
//        new PriorityQueue();


        Queue<Integer> queue = new LinkedList<>();
//        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 10; i >= 0; i--){
            queue.add(i);

        }

        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
            Thread.sleep(1000);
        }
    }
}
