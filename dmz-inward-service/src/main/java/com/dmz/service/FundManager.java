package com.dmz.service;

public class FundManager {

    private static final long serialVersionUID = -1460344858909253047L;

    private String id;

    private String name;

    private String avatarUrl;

    /**
     * @return 基金经理ID
     * @occurs required
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 基金经理姓名
     * @occurs required
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 基金经理头像
     * @occurs required
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
