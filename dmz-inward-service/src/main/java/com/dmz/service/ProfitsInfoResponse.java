package com.dmz.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Matt on 16/5/31.
 */
public class ProfitsInfoResponse  {

    private static final long serialVersionUID = 1464656506151363616L;

    private Map<String,List<FundProfitInfo>> fundProfits;

    /**
     * @return 基金盈亏Map
     * @occurs required
     */
    public Map<String, List<FundProfitInfo>> getFundProfits() {
        return fundProfits;
    }

    public void setFundProfits(Map<String, List<FundProfitInfo>> fundProfits) {
        this.fundProfits = fundProfits;
    }
}
