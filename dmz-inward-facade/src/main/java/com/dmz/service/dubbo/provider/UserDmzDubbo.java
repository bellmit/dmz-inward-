package com.dmz.service.dubbo.provider;

import com.dmz.service.dubbo.invoke.UserDmz;

/**
 * Created by dmz on 2016/3/5.
 */
public class UserDmzDubbo implements UserDmz {
    public String showUserName() {
        return "DMZ .";
    }
}
