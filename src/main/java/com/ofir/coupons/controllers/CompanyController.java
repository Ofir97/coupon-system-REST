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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ofir.coupons.beans.Coupon;
import com.ofir.coupons.beans.CouponsList;
import com.ofir.coupons.custom_exception.AuthorizationException;
import com.ofir.coupons.custom_exception.CouponSystemException;
import com.ofir.coupons.dto.LoginRequestDto;
import com.ofir.coupons.dto.LoginResponseDto;
import com.ofir.coupons.dto.ResponseDto;
import com.ofir.coupons.enums.Category;
import com.ofir.coupons.enums.ClientType;
import com.ofir.coupons.services.CompanyService;
import com.ofir.coupons.utils.LoginManager;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/coupon-system/company")
@RequiredArgsConstructor
@CrossOrigin
public class CompanyController extends ClientController {

	private final LoginManager loginManager;

	@Override
	@PostMapping("/login")
	public LoginResponseDto login(@Valid @RequestBody LoginRequestDto loginRequest) throws AuthorizationException {
		return loginManager.login(loginRequest.getEmail(), loginRequest.getPassword(), ClientType.COMPANY);
	}

	@Override
	@DeleteMapping("/logout")
	public ResponseEntity<?> logout(@RequestHeader("authorization") String token) {
		loginManager.logout(token);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/coupon")
	public ResponseEntity<?> addCoupon(@RequestHeader("authorization") String token, @Valid @RequestBody Coupon coupon)
			throws IOException, CouponSystemException {
		CompanyService service = (CompanyService) loginManager.getService(token);
		return new ResponseEntity<>(new ResponseDto(true, service.addCoupon(coupon).toString()), HttpStatus.CREATED);
	}

	@PutMapping("/coupon")
	public ResponseEntity<?> updateCoupon(@RequestHeader("authorization") String token,
			@Valid @RequestBody Coupon coupon) throws IOException, CouponSystemException {
		CompanyService service = (CompanyService) loginManager.getService(token);
		service.updateCoupon(coupon);
		return new ResponseEntity<>(new ResponseDto(true, "coupon updated successfully"), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/coupon/{id}")
	public ResponseEntity<?> deleteCoupon(@RequestHeader("authorization") String token,
			@PathVariable("id") Integer couponId) throws IOException, CouponSystemException {
		CompanyService service = (CompanyService) loginManager.getService(token);
		service.deleteCoupon(couponId);
		return new ResponseEntity<>(new ResponseDto(true, "coupon deleted successfully"), HttpStatus.ACCEPTED);
	}

	@GetMapping("/coupon")
	public ResponseEntity<?> getCompanyCoupons(@RequestHeader("authorization") String token) {
		CompanyService service = (CompanyService) loginManager.getService(token);
		return new ResponseEntity<>(new CouponsList(service.getCompanyCoupons()), HttpStatus.ACCEPTED);
	}

	@GetMapping("/coupon/{id}")
	public ResponseEntity<?> getOneCoupon(@RequestHeader("authorization") String token,
			@PathVariable("id") Integer couponId) {
		CompanyService service = (CompanyService) loginManager.getService(token);
		return new ResponseEntity<>(service.getOneCoupon(couponId), HttpStatus.ACCEPTED);
	}

	@GetMapping("/coupon/byCategory")
	public ResponseEntity<?> getCompanyCoupons(@RequestHeader("authorization") String token,
			@RequestParam("category") Category category) {
		CompanyService service = (CompanyService) loginManager.getService(token);
		return new ResponseEntity<>(new CouponsList(service.getCompanyCoupons(category)), HttpStatus.ACCEPTED);
	}

	@GetMapping("/coupon/byMaxPrice")
	public ResponseEntity<?> getCompanyCoupons(@RequestHeader("authorization") String token,
			@RequestParam("maxPrice") Double maxPrice) {
		CompanyService service = (CompanyService) loginManager.getService(token);
		return new ResponseEntity<>(new CouponsList(service.getCompanyCoupons(maxPrice)), HttpStatus.ACCEPTED);
	}

	@GetMapping
	public ResponseEntity<?> getCompanyDetails(@RequestHeader("authorization") String token) {
		CompanyService service = (CompanyService) loginManager.getService(token);
		return new ResponseEntity<>(service.getCompanyDetails(), HttpStatus.ACCEPTED);
	}

}
