package dmztest;

import com.alibaba.fastjson.JSON;

/**
 * Created by dmz on 2016/9/21.
 */
public class PropertiesExample {
    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(System.getProperties()));
        System.out.println(JSON.toJSONString(System.getProperty("https.protocols")));
    }
}
