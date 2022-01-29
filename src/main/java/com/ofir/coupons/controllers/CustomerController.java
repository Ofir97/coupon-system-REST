package com.ofir.coupons.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ofir.coupons.beans.CouponsList;
import com.ofir.coupons.beans.LoginDetails;
import com.ofir.coupons.beans.ResponseDto;
import com.ofir.coupons.custom_exception.CouponSystemException;
import com.ofir.coupons.enums.Category;
import com.ofir.coupons.services.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/coupon-system/customer")
@RequiredArgsConstructor
@CrossOrigin
public class CustomerController extends ClientController{

	private final CustomerService customerService;
	
	@Override
	@PostMapping("/login")
	public boolean login(@Valid @RequestBody LoginDetails loginDetails) {
		return customerService.login(loginDetails.getEmail(), loginDetails.getPassword());
	}
	
	@GetMapping("/purchaseCoupon/{id}")
	public ResponseEntity<?> purchaseCoupon(@PathVariable("id") Integer couponId) throws IOException, CouponSystemException {
		customerService.purchaseCoupon(couponId);
		return new ResponseEntity<>(new ResponseDto(true, "coupon purchased successfully"), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/coupon")
	public ResponseEntity<?> getCustomerCoupons() {
		return new ResponseEntity<>(new CouponsList(customerService.getCustomerCoupons()), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/coupon/byCategory")
	public ResponseEntity<?> getCustomerCoupons(@RequestParam("category") Category category) {
		return new ResponseEntity<>(new CouponsList(customerService.getCustomerCoupons(category)), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/coupon/byMaxPrice")
	public ResponseEntity<?> getCustomerCoupons(@RequestParam("maxPrice") Double maxPrice) {
		return new ResponseEntity<>(new CouponsList(customerService.getCustomerCoupons(maxPrice)), HttpStatus.ACCEPTED);
	}
	
	@GetMapping
	public ResponseEntity<?> getCustomerDetails() {
		return new ResponseEntity<>(customerService.getCustomerDetails(), HttpStatus.ACCEPTED);
	}
	


}
