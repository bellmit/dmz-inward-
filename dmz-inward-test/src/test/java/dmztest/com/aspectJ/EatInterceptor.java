package dmztest.com.aspectJ;

/**
 * @author dmz
 * @date 2017/9/14
 */
public class EatInterceptor implements Interceptor {
    @Override
    public void intercept(ActionInvocation invocation) {
        System.out.println("start eat...");
        invocation.invoke();
        System.out.println("end eat...");
    }
}
