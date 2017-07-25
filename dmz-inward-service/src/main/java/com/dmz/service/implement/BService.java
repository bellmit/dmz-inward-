package com.dmz.service.implement;

import com.dmz.basic.model.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author dmz
 * @date 2017/7/7
 */
@Service(value = "bservice")
public class BService implements A {
    private final static Logger logger = LoggerFactory.getLogger(AService.class);
    public void b() {
        logger.info("B");
    }

    @Override
    public void a() {

    }

    @Override
    public void b(Login login) {

    }

    @Override
    public void c(String name) {

    }
}
