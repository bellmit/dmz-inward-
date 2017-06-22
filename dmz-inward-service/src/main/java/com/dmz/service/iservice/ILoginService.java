package com.dmz.service.iservice;

import com.dmz.basic.model.Login;
import com.dmz.basic.model.User;
import com.dmz.basic.exception.DBException;

import java.util.List;

/**
 * Created by dmz on 2016/3/18.
 */
public interface ILoginService {

    void addLoginInfo(Login login) ;

    Login getLoginInfoById(Long id) ;

    Login getLoginInfoByLogin(Login name) ;

    User showUserDetailByLoginName(Login login);

    Boolean checkUserLogin(Login login) ;
}
