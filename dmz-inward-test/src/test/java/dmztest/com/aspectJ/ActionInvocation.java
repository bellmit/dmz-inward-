package dmztest.com.aspectJ;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmz
 * @date 2017/9/14
 */
public class ActionInvocation {
    List<Interceptor> interceptors= new ArrayList<>();
    int index=-1;
    EatMan eatMan = new EatMan();

    public ActionInvocation(){
        this.interceptors.add(new EatInterceptor());
    }
    public void invoke(){
        if (index+1>=this.interceptors.size()) {
            eatMan.eat();
        }else {
            index++;
            this.interceptors.get(index).intercept(this);
        }
    }
}
