package com.dmz.service;

/**
 * Created by dmz on 2016/7/26.
 */
public class AccountRequest {

    private String brokerUserId;

    private String accountId;

    public String getBrokerUserId() {
        return brokerUserId;
    }

    public void setBrokerUserId(String brokerUserId) {
        this.brokerUserId = brokerUserId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
