package com.dmz.service.utils;

import java.util.UUID;

/**
 * Created by dmz on 2016/3/1.
 */
public abstract class GenerateUUID {

    private GenerateUUID(){
    }

    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString().replaceAll("-","");
        return str;
    }
}
