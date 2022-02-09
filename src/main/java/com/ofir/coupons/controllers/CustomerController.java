package com.ofir.coupons.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ofir.coupons.beans.CouponsList;
import com.ofir.coupons.custom_exception.AuthorizationException;
import com.ofir.coupons.custom_exception.CouponSystemException;
import com.ofir.coupons.dto.LoginRequestDto;
import com.ofir.coupons.dto.LoginResponseDto;
import com.ofir.coupons.dto.ResponseDto;
import com.ofir.coupons.enums.Category;
import com.ofir.coupons.enums.ClientType;
import com.ofir.coupons.services.CustomerService;
import com.ofir.coupons.utils.LoginManager;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/coupon-system/customer")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class CustomerController extends ClientController {

	private final LoginManager loginManager;

	@Override
	@PostMapping("/login")
	public LoginResponseDto login(@Valid @RequestBody LoginRequestDto loginRequest) throws AuthorizationException {
		return loginManager.login(loginRequest.getEmail(), loginRequest.getPassword(), ClientType.CUSTOMER);
	}

	@Override
	@DeleteMapping("/logout")
	public ResponseEntity<?> logout(@RequestHeader("authorization") String token) {
		loginManager.logout(token);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/purchaseCoupon/{id}")
	public ResponseEntity<?> purchaseCoupon(@RequestHeader("authorization") String token,
			@PathVariable("id") Integer couponId) throws IOException, CouponSystemException {
		CustomerService service = (CustomerService) loginManager.getService(token);
		service.purchaseCoupon(couponId);
		return new ResponseEntity<>(new ResponseDto(true, "coupon purchased successfully"), HttpStatus.ACCEPTED);
	}

	@GetMapping("/coupon")
	public ResponseEntity<?> getCustomerCoupons(@RequestHeader("authorization") String token) {
		CustomerService service = (CustomerService) loginManager.getService(token);
		return new ResponseEntity<>(new CouponsList(service.getCustomerCoupons()), HttpStatus.ACCEPTED);
	}

	@GetMapping("/coupon/byCategory")
	public ResponseEntity<?> getCustomerCoupons(@RequestHeader("authorization") String token,
			@RequestParam("category") Category category) {
		CustomerService service = (CustomerService) loginManager.getService(token);
		return new ResponseEntity<>(new CouponsList(service.getCustomerCoupons(category)), HttpStatus.ACCEPTED);
	}

	@GetMapping("/coupon/byMaxPrice")
	public ResponseEntity<?> getCustomerCoupons(@RequestHeader("authorization") String token,
			@RequestParam("maxPrice") Double maxPrice) {
		CustomerService service = (CustomerService) loginManager.getService(token);
		return new ResponseEntity<>(new CouponsList(service.getCustomerCoupons(maxPrice)), HttpStatus.ACCEPTED);
	}

	@GetMapping
	public ResponseEntity<?> getCustomerDetails(@RequestHeader("authorization") String token) {
		CustomerService service = (CustomerService) loginManager.getService(token);
		return new ResponseEntity<>(service.getCustomerDetails(), HttpStatus.ACCEPTED);
	}

}
