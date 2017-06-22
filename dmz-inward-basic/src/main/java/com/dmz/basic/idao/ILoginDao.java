package com.dmz.basic.idao;
import com.dmz.basic.model.Login;

/**
 * Created by dmz on 2016/8/19.
 */
public interface ILoginDao {
    void insertLogin(Login record);

    Login selectByPrimaryKey(Long id);

    Login selectLoginByLogin(Login login);
}
