package com.ofir.coupons.custom_exception;

import com.ofir.coupons.enums.ErrorMessage;

public class AuthorizationException extends Exception{
	
	public AuthorizationException(ErrorMessage msg) {
		super(msg.getDescription());
	}

}
