package com.dmz.basic.implement;

import com.alibaba.fastjson.JSON;
import com.dmz.basic.exception.DBException;
import com.dmz.basic.idao.ILoginDao;
import com.dmz.basic.mapper.LoginMapper;
import com.dmz.basic.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dmz on 2016/8/19.
 */
@Repository
public class LoginDao implements ILoginDao {

    @Autowired
    private LoginMapper loginMapper;

    public void insertLogin(Login login){
        try {
            int result = loginMapper.insert(login);
            if (result != 1) {
                throw new DBException.NoReaction("Operation No Reaction.", JSON.toJSONString(login));
            }
        } catch (Exception e) {
            if (e instanceof DBException.NoReaction) {
                throw (DBException.NoReaction) e;
            }
            throw new DBException.DBServerException("DB Service Error", e, JSON.toJSONString(e));
        }
    }

    public Login selectByPrimaryKey(Long id) {
        try {
            List<Login> login = loginMapper.selectByPrimaryKey(id);
            if (login == null || login.isEmpty()) {
                throw new DBException.EmptyData("No Login Data.",JSON.toJSONString(id));
            } else if (login.size() != 1) {
                throw new DBException.MultipleData("Multiple Login Data" ,JSON.toJSONString(login));
            } else {
                return login.get(0);
            }
        } catch (Exception e) {
            if (e instanceof DBException.EmptyData) {
                throw (DBException.EmptyData) e;
            } else if (e instanceof DBException.MultipleData) {
                throw (DBException.MultipleData) e;
            }
            throw new DBException.DBServerException("DB Service Error", e, JSON.toJSONString(e));
        }
    }
    public Login selectLoginByLogin(Login loginParam) {
        List<Login> login = null;
        try {
            login = loginMapper.selectLoginByLogin(loginParam);
        } catch (Exception e) {
            if (e instanceof SQLException) {
                ((SQLException) e).getSQLState();
            }
        }
        return login.get(0);
    }


    //public Login selectLoginByLogin(Login loginParam){
    //
    //    try {
    //        List<Login> login = loginMapper.selectLoginByLogin(loginParam);
    //        if (login == null || login.isEmpty()) {
    //            throw new DBException.EmptyData("No Login Data", JSON.toJSONString(loginParam));
    //        } else if (login.size() != 1) {
    //            throw new DBException.MultipleData("Multiple Login Data", JSON.toJSONString(login));
    //        } else {
    //            return login.get(0);
    //        }
    //    } catch (Exception e) {
    //        if (e instanceof DBException.EmptyData) {
    //            throw (DBException.EmptyData) e;
    //        } else if (e instanceof DBException.MultipleData) {
    //            throw (DBException.MultipleData) e;
    //        }
    //        throw new DBException.DBServerException("DB Service Error", e, JSON.toJSONString(e));
    //    }
    //}
}
