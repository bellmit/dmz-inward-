package com.dmz.web.advisor;
import com.dmz.service.constant.basic.LoginDetail;
import com.dmz.service.iservice.ILoginService;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by dmz on 2016/3/18.
 */
public class LoggerAdvisor implements MethodBeforeAdvice {

    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("Class : "+ target.getClass().getName());
        System.out.println("Method : "+ method.getName());
        for(int i=0; i<args.length; i++){
            System.out.println("Arg : "+ i + " -> " + args[i].toString());
        }
        System.out.println("---------------------------------------------");
        Class clazz = target.getClass();
        Annotation[] anns = clazz.getAnnotations();
        Annotation[] manns = method.getAnnotations();
        System.out.println("Class Annotation:");
        for (Annotation ann:anns){
            System.out.println(ann.toString());
        }
        System.out.println("Method Annotation:");
        for (Annotation mann:manns){
            System.out.println(mann.toString());
        }
        if (method.isAnnotationPresent(LoginDetail.class)){
            LoginDetail loginAnn = method.getAnnotation(LoginDetail.class);
//            Method methods = loginAnn.getClass().getMethod("login");
//            Object name = methods.invoke(loginAnn);
            boolean name = loginAnn.login();
            System.out.println("The data of LoginDetail is "+name);

            System.out.println("...loginDetail.");
        }

        Field[] fields = target.getClass().getDeclaredFields();
        for (Field field:fields) {
            if (ILoginService.class.isAssignableFrom(field.getType())) {
                System.out.println(field);
            }
        }

    }
}
