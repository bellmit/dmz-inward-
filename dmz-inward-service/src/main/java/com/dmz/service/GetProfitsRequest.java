package com.dmz.service;

import java.util.Date;

/**
 * Created by Matt on 16/5/31.
 */
public class GetProfitsRequest {

    private static final long serialVersionUID = 6543306327499297351L;

    private String brokerUserId;

    private String accountId;

    private String paymentMethodId;

    private String fundCode;

    private FeeType shareType;

    private Date beginDate;

    private Date endDate;


    public String getBrokerUserId() {
        return brokerUserId;
    }

    /**
     * @param brokerUserId 投资者在合作平台的用户ID
     * @occurs required
     */
    public void setBrokerUserId(String brokerUserId) {
        this.brokerUserId = brokerUserId;
    }

    public String getAccountId() {
        return accountId;
    }

    /**
     * @param accountId 投资者的盈米财富账户ID
     * @occurs required
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    /**
     * @param paymentMethodId 指定要查询的份额在购买时用的支付方式ID。
     * @occurs optional
     */
    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getFundCode() {
        return fundCode;
    }

    /**
     * @param fundCode 基金代码
     * @occurs optional
     */
    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public FeeType getShareType() {
        return shareType;
    }

    /**
     * @param shareType 份额的收费方式，详见收费方式
     * @occurs optional
     */
    public void setShareType(FeeType shareType) {
        this.shareType = shareType;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * @param beginDate 开始日期（含），程序会截取年月日，忽略时分秒
     * @occurs optional
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate 结束日期（含），程序会截取年月日，忽略时分秒
     * @occurs optional
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
