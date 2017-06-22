package dmztest.com.cglib;

import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * Created by dmz on 2016/2/1.
 */
public class SaySomethingCglibProxy3 {
    private Object object;
    public SaySomethingCglibProxy3(Object object){
        this.object = object;
    }
    public Object proxyCglib(CallbackFilter filter,MethodInterceptor proxy){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.object.getClass());
        enhancer.setCallbacks(new Callback[]{proxy,NoOp.INSTANCE});
        enhancer.setCallbackFilter(filter);
        return enhancer.create();
    }
}
