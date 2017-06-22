package com.dmz.service;

import java.util.Date;

/**
 * @author dmz
 * @tag
 * @memo 2016/6/23
 */
public class FundTradeDateRequest {

    private static final long serialVersionUID = 6992443595084646848L;

    private Date datetime;

    private String fundCode;

    private Integer offset;

    public Date getDatetime() {
        return datetime;
    }

    /**
     * @param datetime 交易日
     * @occurs required
     */
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getFundCode() {
        return fundCode;
    }

    /**
     * @param fundCode 基金Code
     * @occurs required
     */
    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public Integer getOffset() {
        return offset;
    }

    /**
     * @param offset 时间偏移
     * @occurs required
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
