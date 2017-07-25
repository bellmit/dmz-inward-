//package com.dmz.service.implement;
//
//import com.dmz.basic.exception.BusinessException;
//import com.dmz.basic.exception.DBException;
//import com.dmz.basic.idao.ILoginDao;
//import com.dmz.basic.idao.IUserDao;
//import com.dmz.basic.model.Login;
//import com.dmz.basic.model.User;
//import com.dmz.service.iservice.ILoginService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.StringUtils;
//
///**
// * Created by dmz on 2016/3/18.
// */
//@Service
//public class LoginService implements ILoginService {
//
//    private static Logger LOG = LoggerFactory.getLogger(LoginService.class);
//
//    @Autowired
//    private ILoginDao loginDao;
//
//    //@Autowired
//    //private IUserDao userDao;
//
//    @Transactional
//    public void addLoginInfo(Login login) {
//        try {
//            loginDao.insertLogin(login);
//        } catch (DBException.NoReaction noReaction) {
//            noReaction.printStackTrace();
//        } catch (DBException.DBServerException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Login getLoginInfoById(Long id) {
//        Login login = loginDao.selectByPrimaryKey(id);
//        return login;
//    }
//
//    public Login getLoginInfoByLogin(Login loginParam) {
//
//        Login login = null;
//        try {
//            login = loginDao.selectLoginByLogin(loginParam);
//        } catch (DBException.EmptyData emptyData) {
//            emptyData.printStackTrace();
//        } catch (DBException.DBServerException e) {
//            e.printStackTrace();
//        } catch (DBException.MultipleData multipleData) {
//            multipleData.printStackTrace();
//        }
//        return login;
//    }
//
//    //public User showUserDetailByLoginName(Login loginParam) {
//    //
//    //    if (loginParam != null && loginParam.getLoginName() != null) {
//    //        Login login = loginDao.selectLoginByLogin(loginParam);
//    //        User user = new User();
//    //        user.setUserNo(login.getUserNo());
//    //        User userDaoss = userDao.selectUserByUser(user);  // 数据库中刚好一个数据,Mapper中的sql存在问题
//    //        return userDaoss;
//    //    }
//    //
//    //    throw new BusinessException.ParamsException("Bad Params", loginParam.toString());
//    //}
//
//    public Boolean checkUserLogin(Login loginParams) {
//
//        if (loginParams != null && !StringUtils.isEmpty(loginParams.getLoginName()) &&
//                !StringUtils.isEmpty(loginParams.getPasswd())) {
//            try {
//                loginDao.selectLoginByLogin(loginParams);
//                return true;
//            } catch (DBException.EmptyData emptyData) {
//                emptyData.printStackTrace();
//            } catch (DBException.DBServerException e) {
//                e.printStackTrace();
//            } catch (DBException.MultipleData multipleData) {
//                multipleData.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//}
