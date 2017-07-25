package com.dmz.service.filter;

/**
 * @author dmz
 * @date 2017/7/5
 */
public abstract class AbstractTraceContext {

    private String applicationName;

    private String type;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
