package com.dmz.service;

import com.dmz.service.log.LogFilter;

import java.io.Serializable;

/**
 * @author dmz
 * @date 2017/7/5
 */
public class BaseResponse implements Serializable {
    private static final long serialVersionUID = -2587588847373809432L;

    private String respmsg;

    private String rescode;

    public String getRespmsg() {
        return respmsg;
    }

    public void setRespmsg(String respmsg) {
        this.respmsg = respmsg;
    }

    public String getRescode() {
        return rescode;
    }

    public void setRescode(String rescode) {
        this.rescode = rescode;
    }

    @Override
    public String toString() {
        return LogFilter.toFilterString(this);
    }
}
