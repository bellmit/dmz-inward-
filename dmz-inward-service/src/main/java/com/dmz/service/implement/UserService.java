//package com.dmz.service.implement;
//
//import com.dmz.basic.mapper.UserMapper;
//import com.dmz.basic.model.User;
//import com.dmz.service.iservice.IUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * Created by dmz on 2016/4/19.
// */
//@Service
//public class UserService implements IUserService {
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Transactional
//    public int addUserInfo(User user) {
//        return userMapper.insert(user);
//    }
//
//    public User getUserInfoById(Long id) {
//        return userMapper.selectByPrimaryKey(id);
//    }
//
//    public User getUserInfoByUser(User user) {
//        return userMapper.selectUserByUser(user).get(0);
//    }
//}
