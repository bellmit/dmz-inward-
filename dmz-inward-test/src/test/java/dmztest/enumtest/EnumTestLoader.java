package dmztest.enumtest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 * @author dmz
 * @date 2018/1/4
 */
public class EnumTestLoader {
    public static void main(String[] args) {
        System.out.println(Method.POST.ordinal());
        System.out.println(b(Method.GET));
    }

    public static <T> JsonElement b(T obj) {
        return (new Gson()).toJsonTree(obj);
    }
}
