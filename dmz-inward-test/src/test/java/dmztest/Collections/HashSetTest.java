package dmztest.Collections;

import java.util.*;

/**
 * Created by dmz on 2016/8/1.
 */
class Student{
    public Student(String name,int age) {
        this.name = name;
        this.age = age;
    }
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
public class HashSetTest {
//    private static class MyArrayList<T> extends AbstractList<T> {
//
//
//    }
    public static void main(String[] args) {

        new TreeSet<>().add(null);
        new HashSet<>();
        Map<Object, Object> map = Collections.synchronizedMap(new HashMap<>());
        Integer[] items = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<Integer> list = Arrays.asList(items);
        Comparator<Integer> compartor = (o1, o2) -> o1 - o2;
        Comparator<Integer> normal = Integer::compare;
        Collections.sort(list, compartor.reversed());
//        list.stream().forEach(e -> System.out.println(e));

        list.parallelStream().forEachOrdered(e -> System.out.println(e));
//        Student s1 = new Student("dmz1", 10);
//        Student s2 = new Student("dmz2", 20);
//        Student s3 = new Student("dmz3", 30);
//        Student s4 = new Student("dmz4", 40);
//        Student s5 = new Student("dmz4", 30);
//        List<Student> list = new ArrayList<>();
//        list.add(s1);
//        list.add(s2);
//        list.add(s3);
//        list.add(s4);
//        list.add(s5);
//        int sum = list.stream().mapToInt(Student::getAge).sum();
//        Integer summ2 = list.stream().map(Student::getAge).reduce(0, (a, b) -> a + b);
//        System.out.println(JSON.toJSONString(summ2));

//        Stream<Object> it = new HashSet<>().stream().unordered();
//        System.out.println(JSON.toJSONString(it));
//        Map<Integer, List<Student>> groupByAge = list.stream().collect(Collectors.groupingBy(Student::getAge));
//        System.out.println(JSON.toJSONString(groupByAge));
//
//        Map<Boolean, List<Student>> partitionBy = list.stream().collect(Collectors.partitioningBy(items -> items.getAge() > 30));
//        System.out.println(JSON.toJSONString(partitionBy));
//
//        Map<String, List<Student>> groupByName = list.stream().collect(Collectors.groupingBy(Student::getName));
//        System.out.println(JSON.toJSONString(groupByName));
//
//        Map<String, Integer> groupByNameSumming = list.stream().collect(Collectors.groupingBy(Student::getName, Collectors.summingInt(Student::getAge)));
//        System.out.println(JSON.toJSONString(groupByNameSumming));
//
//        Map<String, Map<Integer, List<Student>>> groupByAgeName = list.stream().collect(Collectors.groupingBy(Student::getName, Collectors.groupingBy(Student::getAge)));
//        System.out.println(JSON.toJSONString(groupByAgeName));
//
//        Map<String, List<Integer>> groupByNameName = list.stream().collect(Collectors.groupingBy(Student::getName, Collectors.mapping(Student::getAge, Collectors.toList())));
//        System.out.println(JSON.toJSONString(groupByNameName));
//
//        Map<String, Integer> groupByNameSumAge = list.stream().collect(Collectors.groupingBy(Student::getName, Collectors.reducing(0, Student::getAge, Integer::sum)));
//        System.out.println(groupByNameSumAge);
//
//        Map<String, Integer> groupByNameSumAge2 = list.stream().collect(Collectors.groupingBy(Student::getName, Collectors.summingInt(Student::getAge)));
//        System.out.println(groupByNameSumAge2);
//        Integer summ2 = list.stream().map(Student::getAge).reduce(0, (a, b) -> a + b);

//
//        Map<String, String> map = new HashMap<>();
//        map.put("dmz", "100");
//        map.put("DMZ", "200");
//        Set<String> sets = map.keySet();
//        for (String str : sets) {
//            System.out.println(str);
//        }
//        Collection<String> collections = map.values();
//        for (String str : collections) {
//            System.out.println(str);
//        }
//        Set<Map.Entry<String, String>> entrySets = map.entrySet();
//        for (Map.Entry<String, String> entry : entrySets) {
//            System.out.println(entry.getKey() + "-->" + entry.getValue());
//        }
//        TreeSet<Character> tree = new TreeSet<>();
//        for (char ch='a';ch<='z';) {
//            tree.add(ch++);
//        }
//        for (char ch = 'a'; ch <= 'z'; ) {
////            String from = String.valueOf(ch++);
////            String to = String.valueOf(ch);
//            System.out.println(ch + ": " + tree.subSet(ch, ++ch).size());
//        }
//        System.out.println(JSON.toJSONString(tree));
//        SortedSet<Character> tr = tree.headSet('d');
//        SortedSet<Character> trr = tree.tailSet('y');
//        System.out.println(JSON.toJSONString(tr));
//        System.out.println(JSON.toJSONString(trr));
//        System.out.println(tree.subSet('c','e').size());
//
//        new TreeMap<>().put(null,null);
    }
}
