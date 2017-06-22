package dmztest.Inmmutable;

import com.alibaba.fastjson.JSON;

/**
 * Created by dmz on 2016/8/3.
 */
class People{
    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    private String name;
    private Integer age;

    public People(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
public class InmmutableTest {
    public static void main(String[] args) {
        People people = new People("dmz", 200);

        System.out.println(JSON.toJSONString(people));

        tryModify(people.getName(), people.getAge());

        System.out.println(JSON.toJSONString(people));

    }

    private static void tryModify(String name, Integer age) {
        name = new String("ym");
        age = 2000;
    }
}
