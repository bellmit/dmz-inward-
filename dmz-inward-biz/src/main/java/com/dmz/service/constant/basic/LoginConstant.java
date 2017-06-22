package com.dmz.service.constant.basic;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dmz on 2016/3/2.
 */
public class LoginConstant implements ConstantBase {


    public static String WEB = "web";
    public static String PHONE = "phone";
    public static String WEAK = "weak";
    public static String NORMAL = "normal";
    public static String STRENGTH = "strength";
    public static String UNLOCKED = "unlocked";
    public static String LOCKED = "locked";


    public static Map<String, Integer> PLATFORM = new HashMap();

    public static Map<String, Integer> HAS_HPASSWORD = new HashMap();

    public static Map<String, Integer> PASS_STRENGTH = new HashMap();

    public static Map<String, Integer> STATUS = new HashMap();


    static {
        PLATFORM.put(LoginConstant.WEB, 0);
        PLATFORM.put(LoginConstant.PHONE, 1);
        HAS_HPASSWORD.put(NO, 0);
        HAS_HPASSWORD.put(YES, 1);
        PASS_STRENGTH.put(LoginConstant.WEAK, 0);
        PASS_STRENGTH.put(LoginConstant.NORMAL, 1);
        PASS_STRENGTH.put(LoginConstant.STRENGTH, 2);
        STATUS.put(LoginConstant.UNLOCKED, 0);
        STATUS.put(LoginConstant.LOCKED, 1);
    }

    private LoginConstant() {
    }
}
