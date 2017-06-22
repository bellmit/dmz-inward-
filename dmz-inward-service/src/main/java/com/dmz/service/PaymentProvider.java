package com.dmz.service;

import java.math.BigDecimal;

/**
 * Created by Matt on 16/5/30. 支付信息列表
 */
public class PaymentProvider {

	private static final long serialVersionUID = 6818748205403730925L;

	private String name;

	private String paymentType;

	private BigDecimal maxRapidPayAmountPerTxn;

	private BigDecimal maxRapidPayAmountPerDay;

	private BigDecimal maxRapidPayAmountPerMonth;

	private Integer maxRapidPayTxnCountPerDay;

	/**
	 * @return 支付提供方的名称，例如“工商银行”
	 * @occurs required
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 支付提供方的支付类型，如“bank:003”。该字段可直接用于填充基金购买接口的“paymentType”字段。
	 * @occurs required
	 */
	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * @return 快捷支付单笔最大金额，如果为null表示无限制。
	 * @occurs required
	 */
	public BigDecimal getMaxRapidPayAmountPerTxn() {
		return maxRapidPayAmountPerTxn;
	}

	public void setMaxRapidPayAmountPerTxn(BigDecimal maxRapidPayAmountPerTxn) {
		this.maxRapidPayAmountPerTxn = maxRapidPayAmountPerTxn;
	}

	/**
	 * @return 快捷支付单日最大金额，如果为null表示无限制。
	 * @occurs required
	 */
	public BigDecimal getMaxRapidPayAmountPerDay() {
		return maxRapidPayAmountPerDay;
	}

	public void setMaxRapidPayAmountPerDay(BigDecimal maxRapidPayAmountPerDay) {
		this.maxRapidPayAmountPerDay = maxRapidPayAmountPerDay;
	}

	/**
	 * @return 快捷支付单月最大金额，如果为null表示无限制。
	 * @occurs required
	 */
	public BigDecimal getMaxRapidPayAmountPerMonth() {
		return maxRapidPayAmountPerMonth;
	}

	public void setMaxRapidPayAmountPerMonth(BigDecimal maxRapidPayAmountPerMonth) {
		this.maxRapidPayAmountPerMonth = maxRapidPayAmountPerMonth;
	}

	/**
	 * @return 快捷支付单日最大次数，如果为null表示无限制。
	 * @occurs required
	 */
	public Integer getMaxRapidPayTxnCountPerDay() {
		return maxRapidPayTxnCountPerDay;
	}

	public void setMaxRapidPayTxnCountPerDay(Integer maxRapidPayTxnCountPerDay) {
		this.maxRapidPayTxnCountPerDay = maxRapidPayTxnCountPerDay;
	}
}
