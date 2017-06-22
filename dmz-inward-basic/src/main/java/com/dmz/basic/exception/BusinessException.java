package com.dmz.basic.exception;

import com.dmz.service.exceptions.BasicException;

/**
 * Created by dmz on 2016/11/10.
 */
public class BusinessException {

    public static class ParamsException extends BasicException {

        public ParamsException(String msg) {
            super(msg);
        }

        public ParamsException(String msg, String jsonContext) {
            super(msg, jsonContext);
        }

        public ParamsException(String msg, Throwable ex) {
            super(msg, ex);
        }

        public ParamsException(String msg, Throwable ex, String jsonContext) {
            super(msg, ex, jsonContext);
        }
    }
}
