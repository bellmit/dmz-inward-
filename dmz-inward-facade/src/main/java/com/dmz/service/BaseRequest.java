package com.dmz.service;

import com.dmz.service.log.LogFilter;

import java.io.Serializable;

/**
 * @author dmz
 * @date 2017/7/5
 */
public class BaseRequest implements Serializable {
    private static final long serialVersionUID = 247610478411005984L;

    @Override
    public String toString() {
        return LogFilter.toFilterString(this);
    }
}
