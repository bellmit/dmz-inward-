package com.dmz.service.exceptions;

/**
 * Created by dmz on 2016/11/23.
 */
public class GsonImprovedAdapterException extends BasicException{

    public GsonImprovedAdapterException(String msg) {
        super(msg);
    }

    public GsonImprovedAdapterException(String msg, String jsonContext) {
        super(msg, jsonContext);
    }

    public GsonImprovedAdapterException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public GsonImprovedAdapterException(String msg, Throwable ex, String jsonContext) {
        super(msg, ex, jsonContext);
    }
}
