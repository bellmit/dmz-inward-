package com.dmz.test.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by dmz on 2016/12/6.
 */
@Service
public class ClassRoom {


//    @Resource(name = "ppp")
    @Autowired
    @Qualifier(value = "ppp")
    private People people;
}
