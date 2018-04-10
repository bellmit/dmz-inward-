package dmztest.jdk8Test;

/**
 * @author dmz
 * @date 2018/1/8
 */
public class Person {
    public enum Sex {
        MALE, FEMALE
    }

    private String name;
    private int age;
    private Sex gender;

    public static int compareByAge(Person a, Person b) {
        return a.getAge() - b.getAge();
    }

    public Person(String name, int age, Sex gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
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

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }
}
