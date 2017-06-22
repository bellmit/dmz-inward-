package dmztest;

import com.alibaba.fastjson.JSON;

/**
 * Created by dmz on 2016/9/1.
 */
enum Operator {

    ADD("*") {
        @Override
        public int calculate(int a, int b) {
            return a + b;
        }
    }, DIVIDE("/") {
        @Override
        public int calculate(int a, int b) {
            if (b == 0) {
                throw new IllegalArgumentException();
            }
            return a / b;
        }
    };

    public abstract int calculate(int a, int b);

    public String getOperator() {
        return operator;
    }

    private String operator;

    Operator(String operator) {
        this.operator = operator;
    }
}

enum Seasons {
    spring(0, "3~5月"), summer(1, "6~8月"), autumn(2, "9~11月"), winter(3, "12~2月");
    private int code;

    public String getTime() {
        return time;
    }

    private String time;

    Seasons(int code, String time) {
        this.code = code;
        this.time = time;
    }

    public int getCode() {
        return code;
    }

    public Seasons getSeasonByTime(String time) {
        for (Seasons seasons : values()) {
            if (seasons.equals(time)) {
                return seasons;
            }
        }
        return null;
    }

    public Seasons getSeasonByCode(int code) {
        Seasons[] values = values();
        for (Seasons season : values) {
            if (season.getCode() == code) {
                return season;
            }
        }
        return null;
    }

}

public class EnumExample {
    public static void main(String[] args) {
        Seasons spring = Seasons.spring;
        System.out.println(JSON.toJSONString(spring));
        System.out.println(Seasons.spring.getCode());
        System.out.println(Seasons.spring.getTime());
        int add = Operator.ADD.calculate(10, 20);
        System.out.println(add);
        Operator value = Operator.valueOf("ADD");
        System.out.println(value.getOperator());


        int a = Seasons.spring.getCode();

        Seasons aaa = Seasons.valueOf("spring");
        System.out.println(aaa);



    }
}
