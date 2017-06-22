package com.dmz.service;

import java.math.BigDecimal;

public class FundProfitInfo  {

	private static final long serialVersionUID = 1815420353121637454L;

	private String fundCode;

	private String tradeDate;

	private BigDecimal nav;

	private String shareType;

	private String fundName;

	private String paymentMethodId;

	private BigDecimal totalShare;

	private BigDecimal value;

	private BigDecimal profit;

	private Boolean isEstimated;

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
	 * @return 交易日，格式为：YYYY-MM-DD
	 * @occurs required
	 */
	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	/**
	 * @return 基金净值，单位为“元”，精确到0.0001
	 * @occurs required
	 */
	public BigDecimal getNav() {
		return nav;
	}

	public void setNav(BigDecimal nav) {
		this.nav = nav;
	}

	/**
	 * @return 份额的收费方式，详见收费方式
	 * @occurs required
	 */
	public String getShareType() {
		return shareType;
	}

	public void setShareType(String shareType) {
		this.shareType = shareType;
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
	 * @return 份额的支付方式ID
	 * @occurs required
	 */
	public String getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(String paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	/**
	 * @return 持有份额，单位为“份”，精确到0.01
	 * @occurs required
	 */
	public BigDecimal getTotalShare() {
		return totalShare;
	}

	public void setTotalShare(BigDecimal totalShare) {
		this.totalShare = totalShare;
	}

	/**
	 * @return 份额市值，单位为“元”，精确到0.01
	 * @occurs required
	 */
	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	/**
	 * @return 当日盈亏，单位为“元”，精确到0.01
	 * @occurs required
	 */
	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	/**
	 * @return 是否是预估盈亏
	 * @occurs required
	 */
	public Boolean getEstimated() {
		return isEstimated;
	}

	public void setEstimated(Boolean estimated) {
		isEstimated = estimated;
	}
}
