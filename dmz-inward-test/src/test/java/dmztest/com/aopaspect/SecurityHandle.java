package dmztest.com.aopaspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by dmz on 2016/2/2.
 */
public class SecurityHandle {
    public void checkSecurity(JoinPoint joinPoint){
        //取得参数.
//        for(int i=0;i<joinPoint.getArgs().length;i++)
//        {
//            System.out.println(joinPoint.getArgs()[i]);
//        }
//        System.out.println(joinPoint.getSignature().getName());
        System.out.println("check security...");
    }
    public Object  aroundSecurity(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime(); //相当于 before advice
        Object o = pjp.proceed();
        long end = System.nanoTime();  //相当于 after advice
//        long time = (end-start)/1000000;
        System.out.println(end-start);
        return o;
    }
}
