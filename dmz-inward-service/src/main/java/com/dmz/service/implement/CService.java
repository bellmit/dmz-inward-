package com.dmz.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author dmz
 * @date 2017/7/7
 */
@Service
public class CService {
    private final static Logger logger = LoggerFactory.getLogger(AService.class);
    public void c() {
        logger.info("C");
    }
}
