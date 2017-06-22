package com.dmz.service.exceptions;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by dmz on 2016/8/19.
 */
public abstract class BasicException extends RuntimeException {

    private String message;

    private Throwable ex;

    private String jsonContext;

    public BasicException(String msg) {
        super(msg);
        this.message = msg;
    }

    public BasicException(String msg, String jsonContext) {
        this.message = msg;
        this.jsonContext = jsonContext;
    }
    public BasicException(String msg, Throwable ex) {
        super(msg);
        this.message = msg;
        this.ex = ex;
    }

    public BasicException(String msg, Throwable ex, String jsonContext) {
        super(msg);
        this.message = msg;
        this.ex = ex;
        this.jsonContext = jsonContext;
    }

    public String toString() {
        try {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        } catch (Exception var2) {
            var2.printStackTrace();
            return super.toString();
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getEx() {
        return ex;
    }

    public void setEx(Throwable ex) {
        this.ex = ex;
    }

    public String getJsonContext() {
        return jsonContext;
    }

    public void setJsonContext(String jsonContext) {
        this.jsonContext = jsonContext;
    }
}
