package com.dmz.service.implement;

import com.dmz.basic.mapper.UserMapper;
import com.dmz.basic.model.Login;
import com.dmz.basic.model.User;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author dmz
 * @date 2017/7/7
 */
@Service
@Validated
public class AService{
    private final static Logger logger = LoggerFactory.getLogger(AService.class);

    public AService() {
        System.out.println("---------HAHA------------");
    }
    @Resource
    private A bService;

    @Resource
    private UserMapper userMapper;

    //@Resource(name = "ym")
    //private UserDmz ym;

    public void a() {
        //ym.showUserName();
        List<User> users = userMapper.selectAll();
        System.out.println(users);
        bService.a();
        userMapper.selectAll();
        userMapper.selectAll();
        userMapper.selectAll();
        userMapper.selectAll();

    }

    public void b(@Valid Login login) {
        System.out.println(login);
    }

    public void c(@NotBlank String name) {
        System.out.println(name);
    }
}
