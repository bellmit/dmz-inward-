package com.dmz.service;

public class TradeRate{

    private static final long serialVersionUID = 8687626539788851875L;

    private String limitUnit;

    private String lowerLimit;

    private String upperLimit;

    private Boolean lowerLimitInclusive;

    private Boolean upperLimitInclusive;

    private String feeRatio;

    private String fixedFee;

    /**
     * @return 表示上下限的单位
     * @occurs required
     */
    public String getLimitUnit() {
        return limitUnit;
    }

    public void setLimitUnit(String limitUnit) {
        this.limitUnit = limitUnit;
    }

    /**
     * @return 表示区间下限
     * @occurs required
     */
    public String getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(String lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    /**
     * @return 表示区间上限, 特别的，“INF”表示无限大
     * @occurs required
     */
    public String getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(String upperLimit) {
        this.upperLimit = upperLimit;
    }

    /**
     * @return 表示下限是否是闭区间
     * @occurs required
     */
    public Boolean getLowerLimitInclusive() {
        return lowerLimitInclusive;
    }

    public void setLowerLimitInclusive(Boolean lowerLimitInclusive) {
        this.lowerLimitInclusive = lowerLimitInclusive;
    }

    /**
     * @return 表示上限是否是闭区间
     * @occurs required
     */
    public Boolean getUpperLimitInclusive() {
        return upperLimitInclusive;
    }

    public void setUpperLimitInclusive(Boolean upperLimitInclusive) {
        this.upperLimitInclusive = upperLimitInclusive;
    }

    /**
     * @return 表示费率，单位1%，精确到0.01
     * @occurs required
     */
    public String getFeeRatio() {
        return feeRatio;
    }

    public void setFeeRatio(String feeRatio) {
        this.feeRatio = feeRatio;
    }

    /**
     * @return 表示固定费率，单位元，精确到0.01
     * 值不可同时为null。null，表示该值不存在。
     * @occurs required
     */
    public String getFixedFee() {
        return fixedFee;
    }

    public void setFixedFee(String fixedFee) {
        this.fixedFee = fixedFee;
    }
}
