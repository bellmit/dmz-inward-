package com.dmz.basic.idao;

import com.dmz.basic.exception.DBException;
import com.dmz.basic.model.User;

/**
 * Created by dmz on 2016/8/19.
 */
public interface IUserDao {
    User selectUserByUser(User user) throws DBException.EmptyData, DBException.MultipleData, DBException.DBServerException;
}
