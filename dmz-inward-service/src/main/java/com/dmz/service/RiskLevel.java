package com.dmz.service;

import com.dmz.service.adapters.SuperEnum;

/**
 * Created by dmz on 2016/11/22.
 */
public enum RiskLevel implements SuperEnum {

    //未评估
    NOT_ESTIMATED("0"),

    //保守型
    GUARD("1"),

    //稳健型
    STRONG("2"),


    //进取型
    SHARP("3");

    private String value;

    RiskLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public static RiskLevel parse(String value) {

        for (RiskLevel riskLevel : values()) {
            if (riskLevel.value.equals(value)) {
                return riskLevel;
            }
        }
        return null;
    }
}
