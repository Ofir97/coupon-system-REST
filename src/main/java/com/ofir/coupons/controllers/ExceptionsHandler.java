package com.ofir.coupons.controllers;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ofir.coupons.beans.ResponseDto;
import com.ofir.coupons.custom_exception.CouponSystemException;
import com.ofir.coupons.utils.Logger;

import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionsHandler extends ResponseEntityExceptionHandler{
	
	private final Logger logger;
	private String errMsg;
	
	@ExceptionHandler(CouponSystemException.class)
	public ResponseEntity<?> handleCouponSystemException(Exception e) {
		return new ResponseEntity<>(new ResponseDto(false, e.getMessage()), HttpStatus.OK);
	}
	
	@Override // validation errors
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		errMsg = "";
		
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			errMsg+=error.getDefaultMessage() + " ";
		});
		
		try {
			logger.logException(errMsg);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(new ResponseDto(false, errMsg), HttpStatus.OK);
	}
}
