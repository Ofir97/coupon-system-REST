package com.ofir.coupons.enums;

public enum ErrorMessage {

	COMPANY_NAME_OR_EMAIL_EXISTS("company name or email already exists."),
	COMPANY_NAME_UPDATED("company name cannot be updated."),
	COMPANY_NOT_FOUND("company does not exist."),
	
	CUSTOMER_EMAIL_EXISTS("customer email already exists."),
	CUSTOMER_NOT_FOUND("customer does not exist."),
	
	COUPON_TITLE_EXISTS("coupon title already exists in company's coupons."),
	COUPON_NOT_FOUND("coupon does not exist."),
	COUPON_ALREADY_PURCHASED("coupon has already been purchased."),
	COUPON_SOLD_OUT("coupon is sold out."),
	COUPON_EXPIRED("coupon has expired."),
	
	BAD_LOGIN("LOGIN FAILED: bad credentials."),
	INVALID_DATES("start date must be before end date.");
	
	private String description;
	
	ErrorMessage(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
