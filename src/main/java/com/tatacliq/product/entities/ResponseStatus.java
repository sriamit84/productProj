package com.tatacliq.product.entities;

public enum ResponseStatus {

	FAILED("failed"), SUCCESS("success");

	private final String code;

	public String getCode() {
		return code;
	}

	ResponseStatus(String code) {

		this.code = code;
	}

}
