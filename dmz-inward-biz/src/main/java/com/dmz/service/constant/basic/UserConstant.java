package com.dmz.service.constant.basic;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dmz on 2016/4/19.
 */
public class UserConstant implements ConstantBase {

    public static String MAN = "man";
    public static String WOMAN = "woman";
    public static String SECRET = "secret";

    public static Map<String,Integer> GENDER = new HashMap();
    public static Map<String,Integer> IS_SINGLE = new HashMap();
    public static Map<String,Integer> IS_STRAIGHT = new HashMap();

    static {
        GENDER.put(UserConstant.MAN,1);
        GENDER.put(UserConstant.WOMAN,0);
        GENDER.put(UserConstant.SECRET,2);
        IS_SINGLE.put(YES,1);
        IS_SINGLE.put(NO,0);
        IS_SINGLE.put(UserConstant.SECRET,2);
        IS_STRAIGHT.put(YES,1);
        IS_STRAIGHT.put(NO,0);
        IS_STRAIGHT.put(UserConstant.SECRET,2);

    }

}
