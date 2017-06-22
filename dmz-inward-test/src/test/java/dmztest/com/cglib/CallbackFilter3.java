package dmztest.com.cglib;

import org.springframework.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * Created by dmz on 2016/2/1.
 */
public class CallbackFilter3 implements CallbackFilter {
    public int accept(Method method) {
        //拦截query方法
        if("query".equalsIgnoreCase(method.getName()))
            return 0;
        return 1;
    }
}
