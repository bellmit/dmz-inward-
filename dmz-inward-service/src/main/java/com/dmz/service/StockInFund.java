package com.dmz.service;

public class StockInFund {

    private static final long serialVersionUID = -7492740507408079190L;

    private String code;

    private String name;

    private String ratio;

    private String value;

    /**
     * @return 股票代码
     * @occurs required
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return 股票名称
     * @occurs required
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 持仓比例
     * @occurs required
     */
    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }


    /**
     * @return 股票额度
     * @occurs required
     */
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
