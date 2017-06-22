package com.dmz.service;

public enum FeeType {

	// 前端收费
	FRONT_END("A"),

	// 后端收费
	BACK_END("B"),

	// C类收费
	C_TYPE("C");

	private String feeType;

	private FeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getFeeType() {
		return this.feeType;
	}
}
