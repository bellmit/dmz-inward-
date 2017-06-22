package com.dmz.service.iservice;
import com.dmz.basic.model.User;

/**
 * Created by dmz on 2016/4/19.
 */
public interface IUserService {
    int addUserInfo(User user);
    User getUserInfoById(Long id);
    User getUserInfoByUser(User user);
}
