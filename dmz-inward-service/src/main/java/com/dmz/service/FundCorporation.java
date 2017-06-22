package com.dmz.service;

public class FundCorporation {


    private static final long serialVersionUID = -5118882944693074229L;
    private String id;

    private String name;

    /**
     * @return 基金公司ID
     * @occurs required
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 基金公司名称
     * @occurs required
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
