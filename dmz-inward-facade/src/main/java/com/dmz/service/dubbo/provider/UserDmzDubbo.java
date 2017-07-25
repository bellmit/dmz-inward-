package com.dmz.service.dubbo.provider;

import com.dmz.service.dubbo.invoke.UserDmz;
import com.dmz.service.implement.AService;

import javax.annotation.Resource;

/**
 * Created by dmz on 2016/3/5.
 */
public class UserDmzDubbo implements UserDmz {

    @Resource
    private AService aService;

    public void showUserName() {
        aService.a();
    }
}
