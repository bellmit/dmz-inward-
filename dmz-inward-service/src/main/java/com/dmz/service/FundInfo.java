package com.dmz.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author dmz
 * @tag 基金信息
 * @memo 2016/6/23
 */
public class FundInfo {

    private static final long serialVersionUID = 8016103828181806373L;

    private String fundCode;

    private String fundFullName;

    private String fundName;

    private String fundType;

    private String riskLevel;

    private Integer confirmPace;

    private Integer refundPace;

    private String defaultDividendMethod;

    private String allowUpdateDividendMethod;

    private String currency;

    private String onSale;

    private List<String> shareType;

    private String supportPeriodic;

    private String supportAllot;

    private String supportSubscribe;

    private String supportBuy;

    private String supportConvert;

    private BigDecimal saleRate;

    private BigDecimal managerRate;

    private BigDecimal trusteeRate;

    private List<TradeRate> subscribeRate;

    private List<TradeRate> allotRate;

    private List<TradeRate> redeemRate;

    private BigDecimal minIndividualSubscribeAmount;

    private BigDecimal minIndividualAppendSubscribeAmount;

    private BigDecimal maxIndividualSubscribeAmount;

    private BigDecimal minIndividualAllotAmount;

    private BigDecimal minIndividualAppendAllotAmount;

    private BigDecimal maxIndividualAllotAmount;

    private BigDecimal minIndividualPeriodicAmount;

    private BigDecimal maxIndividualPeriodicAmount;

    private BigDecimal minIndividualHoldVol;

    private BigDecimal minIndividualRedeemVol;

    private BigDecimal minIndividualConvertVol;

    private Date setupDate;

    private FundCorporation fundCorp;

    private String trustee;

    private List<FundManager> fundManager;

    private Date reportDate;

    private BigDecimal assetAmount;

    private BigDecimal assetVol;

    private List<StockInFund> stockPortfolio;

    private List<InvestIndustry> industryPortfolio;

    private List<InvestAsset> assetPortfolio;

    /**
     * @return 基金代码
     * @occurs required
     */
    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    /**
     * @return 基金全称
     * @occurs required
     */
    public String getFundFullName() {
        return fundFullName;
    }

    public void setFundFullName(String fundFullName) {
        this.fundFullName = fundFullName;
    }

    /**
     * @return 基金名称
     * @occurs required
     */
    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    /**
     * @return 基金类型
     * @occurs required
     */
    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    /**
     * @return 风险等级
     * @occurs required
     */
    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    /**
     * @return 确认天数
     * @occurs required
     */
    public Integer getConfirmPace() {
        return confirmPace;
    }

    public void setConfirmPace(Integer confirmPace) {
        this.confirmPace = confirmPace;
    }

    /**
     * @return 赎回到账天数
     * @occurs required
     */
    public Integer getRefundPace() {
        return refundPace;
    }

    public void setRefundPace(Integer refundPace) {
        this.refundPace = refundPace;
    }

    /**
     * @return 默认分红方式
     * @occurs required
     */
    public String getDefaultDividendMethod() {
        return defaultDividendMethod;
    }

    public void setDefaultDividendMethod(String defaultDividendMethod) {
        this.defaultDividendMethod = defaultDividendMethod;
    }

    /**
     * @return 是否允许修改分红方式 不可销售的基金为null
     * @occurs required
     */
    public String getAllowUpdateDividendMethod() {
        return allowUpdateDividendMethod;
    }

    public void setAllowUpdateDividendMethod(String allowUpdateDividendMethod) {
        this.allowUpdateDividendMethod = allowUpdateDividendMethod;
    }

    /**
     * @return 币种
     * @occurs required
     */
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return 是否在盈米代销
     * @occurs required
     */
    public String getOnSale() {
        return onSale;
    }

    public void setOnSale(String onSale) {
        this.onSale = onSale;
    }

    /**
     * @return 收费类型
     * @occurs required
     */
    public List<String> getShareType() {
        return shareType;
    }

    public void setShareType(List<String> shareType) {
        this.shareType = shareType;
    }

    /**
     * @return 定投开通标志
     * @occurs required
     */
    public String getSupportPeriodic() {
        return supportPeriodic;
    }

    public void setSupportPeriodic(String supportPeriodic) {
        this.supportPeriodic = supportPeriodic;
    }

    /**
     * @return 申购开通标志
     * @occurs required
     */
    public String getSupportAllot() {
        return supportAllot;
    }

    public void setSupportAllot(String supportAllot) {
        this.supportAllot = supportAllot;
    }

    /**
     * @return 认购开通标志
     * @occurs required
     */
    public String getSupportSubscribe() {
        return supportSubscribe;
    }

    public void setSupportSubscribe(String supportSubscribe) {
        this.supportSubscribe = supportSubscribe;
    }

    /**
     * @return 购买开通标志
     * @occurs required
     */
    public String getSupportBuy() {
        return supportBuy;
    }

    public void setSupportBuy(String supportBuy) {
        this.supportBuy = supportBuy;
    }

    /**
     * @return 转换开通标志
     * @occurs required
     */
    public String getSupportConvert() {
        return supportConvert;
    }

    public void setSupportConvert(String supportConvert) {
        this.supportConvert = supportConvert;
    }

    /**
     * @return 销售服务费
     * @occurs required
     */
    public BigDecimal getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(BigDecimal saleRate) {
        this.saleRate = saleRate;
    }

    /**
     * @return 管理费率
     * @occurs required
     */
    public BigDecimal getManagerRate() {
        return managerRate;
    }

    public void setManagerRate(BigDecimal managerRate) {
        this.managerRate = managerRate;
    }

    /**
     * @return 托管费率
     * @occurs required
     */

    public BigDecimal getTrusteeRate() {
        return trusteeRate;
    }

    public void setTrusteeRate(BigDecimal trusteeRate) {
        this.trusteeRate = trusteeRate;
    }

    /**
     * @return 前端认购费率
     * @occurs required
     */
    public List<TradeRate> getSubscribeRate() {
        return subscribeRate;
    }

    public void setSubscribeRate(List<TradeRate> subscribeRate) {
        this.subscribeRate = subscribeRate;
    }

    /**
     * @return 前端申购费率
     * @occurs required
     */
    public List<TradeRate> getAllotRate() {
        return allotRate;
    }

    public void setAllotRate(List<TradeRate> allotRate) {
        this.allotRate = allotRate;
    }

    /**
     * @return 赎回费率
     * @occurs required
     */
    public List<TradeRate> getRedeemRate() {
        return redeemRate;
    }

    public void setRedeemRate(List<TradeRate> redeemRate) {
        this.redeemRate = redeemRate;
    }

    /**
     * @return 个人首次认购最低金额
     * @occurs required
     */
    public BigDecimal getMinIndividualSubscribeAmount() {
        return minIndividualSubscribeAmount;
    }

    public void setMinIndividualSubscribeAmount(BigDecimal minIndividualSubscribeAmount) {
        this.minIndividualSubscribeAmount = minIndividualSubscribeAmount;
    }

    /**
     * @return 个人追加认购最低金额
     * @occurs required
     */
    public BigDecimal getMinIndividualAppendSubscribeAmount() {
        return minIndividualAppendSubscribeAmount;
    }

    public void setMinIndividualAppendSubscribeAmount(BigDecimal minIndividualAppendSubscribeAmount) {
        this.minIndividualAppendSubscribeAmount = minIndividualAppendSubscribeAmount;
    }

    /**
     * @return 个人最高认购金额
     * @occurs required
     */
    public BigDecimal getMaxIndividualSubscribeAmount() {
        return maxIndividualSubscribeAmount;
    }

    public void setMaxIndividualSubscribeAmount(BigDecimal maxIndividualSubscribeAmount) {
        this.maxIndividualSubscribeAmount = maxIndividualSubscribeAmount;
    }

    /**
     * @return 个人首次申购最低金额
     * @occurs required
     */
    public BigDecimal getMinIndividualAllotAmount() {
        return minIndividualAllotAmount;
    }

    public void setMinIndividualAllotAmount(BigDecimal minIndividualAllotAmount) {
        this.minIndividualAllotAmount = minIndividualAllotAmount;
    }

    /**
     * @return 个人追加申购最低金额
     * @occurs required
     */
    public BigDecimal getMinIndividualAppendAllotAmount() {
        return minIndividualAppendAllotAmount;
    }

    public void setMinIndividualAppendAllotAmount(BigDecimal minIndividualAppendAllotAmount) {
        this.minIndividualAppendAllotAmount = minIndividualAppendAllotAmount;
    }

    /**
     * @return 个人最高申购金额
     * @occurs required
     */
    public BigDecimal getMaxIndividualAllotAmount() {
        return maxIndividualAllotAmount;
    }

    public void setMaxIndividualAllotAmount(BigDecimal maxIndividualAllotAmount) {
        this.maxIndividualAllotAmount = maxIndividualAllotAmount;
    }

    /**
     * @return 个人定投申购最低金额
     * @occurs required
     */
    public BigDecimal getMinIndividualPeriodicAmount() {
        return minIndividualPeriodicAmount;
    }

    public void setMinIndividualPeriodicAmount(BigDecimal minIndividualPeriodicAmount) {
        this.minIndividualPeriodicAmount = minIndividualPeriodicAmount;
    }

    /**
     * @return 个人定投申购最高金额
     * @occurs required
     */
    public BigDecimal getMaxIndividualPeriodicAmount() {
        return maxIndividualPeriodicAmount;
    }

    public void setMaxIndividualPeriodicAmount(BigDecimal maxIndividualPeriodicAmount) {
        this.maxIndividualPeriodicAmount = maxIndividualPeriodicAmount;
    }

    /**
     * @return 个人持有最低份额
     * @occurs required
     */
    public BigDecimal getMinIndividualHoldVol() {
        return minIndividualHoldVol;
    }

    public void setMinIndividualHoldVol(BigDecimal minIndividualHoldVol) {
        this.minIndividualHoldVol = minIndividualHoldVol;
    }

    /**
     * @return 个人赎回最低份额
     * @occurs required
     */
    public BigDecimal getMinIndividualRedeemVol() {
        return minIndividualRedeemVol;
    }

    public void setMinIndividualRedeemVol(BigDecimal minIndividualRedeemVol) {
        this.minIndividualRedeemVol = minIndividualRedeemVol;
    }

    /**
     * @return 个人转换最低份额
     * @occurs required
     */
    public BigDecimal getMinIndividualConvertVol() {
        return minIndividualConvertVol;
    }

    public void setMinIndividualConvertVol(BigDecimal minIndividualConvertVol) {
        this.minIndividualConvertVol = minIndividualConvertVol;
    }

    /**
     * @return 成立日期
     * @occurs required
     */
    public Date getSetupDate() {
        return setupDate;
    }

    public void setSetupDate(Date setupDate) {
        this.setupDate = setupDate;
    }

    /**
     * @return 管理人
     * @occurs required
     */
    public FundCorporation getFundCorp() {
        return fundCorp;
    }

    public void setFundCorp(FundCorporation fundCorp) {
        this.fundCorp = fundCorp;
    }

    /**
     * @return 托管人
     * @occurs required
     */
    public String getTrustee() {
        return trustee;
    }

    public void setTrustee(String trustee) {
        this.trustee = trustee;
    }

    /**
     * @return 基金经理
     * @occurs required
     */
    public List<FundManager> getFundManager() {
        return fundManager;
    }

    public void setFundManager(List<FundManager> fundManager) {
        this.fundManager = fundManager;
    }

    /**
     * @return 报告日期
     * @occurs required
     */
    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    /**
     * @return 资产规模
     * @occurs required
     */
    public BigDecimal getAssetAmount() {
        return assetAmount;
    }

    public void setAssetAmount(BigDecimal assetAmount) {
        this.assetAmount = assetAmount;
    }

    /**
     * @return 份额规模
     * @occurs required
     */
    public BigDecimal getAssetVol() {
        return assetVol;
    }

    public void setAssetVol(BigDecimal assetVol) {
        this.assetVol = assetVol;
    }

    /**
     * @return 十大重仓股
     * @occurs required
     */
    public List<StockInFund> getStockPortfolio() {
        return stockPortfolio;
    }

    public void setStockPortfolio(List<StockInFund> stockPortfolio) {
        this.stockPortfolio = stockPortfolio;
    }

    /**
     * @return 行业配置
     * @occurs required
     */
    public List<InvestIndustry> getIndustryPortfolio() {
        return industryPortfolio;
    }

    public void setIndustryPortfolio(List<InvestIndustry> industryPortfolio) {
        this.industryPortfolio = industryPortfolio;
    }

    /**
     * @return 资产配置
     * @occurs required
     */
    public List<InvestAsset> getAssetPortfolio() {
        return assetPortfolio;
    }

    public void setAssetPortfolio(List<InvestAsset> assetPortfolio) {
        this.assetPortfolio = assetPortfolio;
    }
}
