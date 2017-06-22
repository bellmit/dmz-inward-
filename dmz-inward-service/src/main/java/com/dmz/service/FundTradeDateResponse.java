package com.dmz.service;

import java.util.Date;

/**
 * @author dmz
 * @tag 基金交易日
 * @memo 2016/6/23
 */
public class FundTradeDateResponse {

    private static final long serialVersionUID = -9191529333252175331L;

    private Date tradeDate;

    /**
     * @return 获得基金交易日期
     */
    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }
}
