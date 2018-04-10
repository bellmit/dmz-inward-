package dmztest.jdk8Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author dmz
 * @date 2018/1/4
 */
public class LambdaTest {
    public static void main(String[] args) {
        List<Person> personList = Arrays.asList(new Person("Mark22", 22, Person.Sex.MALE), new Person("Mark21", 21, Person.Sex.MALE),
                new Person("Mary17", 17, Person.Sex.FEMALE), new Person("Mary20", 20, Person.Sex.FEMALE));
        //printPersonAgeOlderThan(personList, 18);

        //printPersonAgeOlderThanPredicate(personList, person -> person.getAge() > 20);

        //printPersonAgeOlderThanCompareCheck(personList, person -> person.getAge() > 20);

        Person[] personArray = personList.toArray(new Person[personList.size()]);

        Arrays.sort(personArray, Person::compareByAge);

        for (Person person : personArray) {
            System.out.println(person.getName());
        }

    }

    public static void printPersonAgeOlderThanCompareCheck(List<Person> personList, CompareCheck<Person> compareCheck) {
        for (Person person : personList) {
            if (compareCheck.test(person)) {
                System.out.println(person.getName());
            }
        }
    }

    public static void printPersonAgeOlderThanPredicate(List<Person> personList, Predicate<Person> predicate) {
        for (Person person : personList) {
            if (predicate.test(person)) {
                System.out.println(person.getName());
            }
        }
    }

    public static void printPersonAgeOlderThan(List<Person> personList, int age) {

        for (Person person : personList) {
            if (person.getAge() > age) {
                System.out.println(person.getName());
            }
        }

    }
}
