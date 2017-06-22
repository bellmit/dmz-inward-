package dmztest.com.aopaspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by dmz on 2016/2/2.
 */
@Aspect
@Component
public class SecurityHandleWithComponent {
    @Pointcut("execution(* com.aopaspect.*.say*(..))") //定制Pointcut,该方法只是作为标识
    private void sayMethod(){}

//    @Before("") //定义advice，应用到那些Pointcut订阅的Joinpoint上,
//    private void toSomeOne(JoinPoint joinPoint) {
//        System.out.println(joinPoint.getSignature().getClass()+"--"+joinPoint.getSignature().getName()+"--"+joinPoint.getArgs()[0]);
//        System.out.println("Before advice...");
//    }

    @After("sayMethod()")//定义after
    private void afterToSomeOne(){
        System.out.println("After advice...");
    }

    @AfterReturning(value = "execution(* com.aopaspect.*.returnSay*(..))",returning = "name")
    private void afterReturnToSomeOne(String name){
        System.out.println("This is Returning..."+name);
    }

    @After(value ="execution(* com.aopaspect.*.returnSay*(..)) && args(dmz)")
    private void afterSomeOne(String dmz){
        System.out.println("This is after..."+dmz);
    }
//    @Around("") //环绕
//    private Object aroundToSomeOne(ProceedingJoinPoint joinPoint) throws Throwable {
//        long start = System.nanoTime(); //before
//        Object object = joinPoint.proceed();
//        String name = joinPoint.getArgs()[0].toString();
//        long end = System.nanoTime(); //after
//        long time = end - start;
//        System.out.println("Around:"+name+"--"+time);
//        return object;
//    }
}
