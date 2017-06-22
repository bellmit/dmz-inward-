package com.dmz.service;

public class InvestIndustry  {


    private static final long serialVersionUID = -875901659699198801L;

    private String name;

    private String ratio;

    private String value;


    /**
     * @return 行业名称
     * @occurs required
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 比重
     * @occurs required
     */
    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    /**
     * @return 额度
     * @occurs required
     */
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
