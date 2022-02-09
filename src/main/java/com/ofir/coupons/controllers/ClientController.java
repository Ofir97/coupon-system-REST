package com.ofir.coupons.controllers;

import org.springframework.http.ResponseEntity;

import com.ofir.coupons.custom_exception.AuthorizationException;
import com.ofir.coupons.dto.LoginRequestDto;
import com.ofir.coupons.dto.LoginResponseDto;

public abstract class ClientController {

	public abstract LoginResponseDto login(LoginRequestDto loginRequest) throws AuthorizationException;
	public abstract ResponseEntity<?> logout(String token);

}
