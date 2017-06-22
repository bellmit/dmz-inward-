package com.dmz.service;

public class InvestAsset{

    private static final long serialVersionUID = -5849632272934197316L;

    private String name;

    private String ratio;

    private String value;

    /**
     * @return 资产名称
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
     * @return 持仓金额
     * @occurs required
     */
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
