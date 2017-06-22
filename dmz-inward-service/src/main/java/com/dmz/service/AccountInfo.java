package com.dmz.service;

/**
 * Created by dmz on 2016/7/26.
 */
public class AccountInfo {

    private String accountId;

    private String accountName;

    private String identityType;

    private String identityNo;

    private String phone;

    private String email;

    //    private String riskGrade;
    private RiskLevel riskGrade;

    private String active;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RiskLevel getRiskGrade() {
        return riskGrade;
    }

    public void setRiskGrade(RiskLevel riskGrade) {
        this.riskGrade = riskGrade;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
