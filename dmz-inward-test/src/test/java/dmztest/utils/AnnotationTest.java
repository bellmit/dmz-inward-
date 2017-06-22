package dmztest.utils;//package com.dmz.utilsTest;
//import com.alibaba.fastjson.JSON;
//import com.dmz.constant.basic.LoginDetail;
//import com.dmz.controller.LoginFront;
//import org.junit.Test;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Method;
//
///**
// * Created by dmz on 2016/3/31.
// */
//public class AnnotationTest {
//    @Test
//    public void testAnnotation() throws NoSuchMethodException {
////        Package[] pack = Package.getPackages();
////        for (Package p:pack){
////            System.out.println(p.toString());
////        }
//        LoginFront loginFront = new LoginFront();
//        Class login = loginFront.getClass();
//        Annotation[] an = login.getAnnotations();
//        for (Annotation p:an){
//            System.out.println(p.toString());
//        }
//        Method method = login.getMethod("LoginName",JSON.class);
//        boolean has = method.isAnnotationPresent(LoginDetail.class);
//        System.out.println(has);
//        LoginDetail ann = method.getAnnotation(LoginDetail.class);
//        System.out.println(ann.toString());
//        System.out.println(ann.login());
//    }
//}
