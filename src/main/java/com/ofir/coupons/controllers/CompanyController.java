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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ofir.coupons.beans.Coupon;
import com.ofir.coupons.beans.CouponsList;
import com.ofir.coupons.beans.LoginDetails;
import com.ofir.coupons.beans.ResponseDto;
import com.ofir.coupons.custom_exception.CouponSystemException;
import com.ofir.coupons.enums.Category;
import com.ofir.coupons.services.CompanyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/coupon-system/company")
@RequiredArgsConstructor
@CrossOrigin
public class CompanyController extends ClientController {

	private final CompanyService companyService;

	@Override
	@PostMapping("/login")
	public boolean login(@Valid @RequestBody LoginDetails loginDetails) {
		return companyService.login(loginDetails.getEmail(), loginDetails.getPassword());
	}

	@PostMapping("/coupon")
	public ResponseEntity<?> addCoupon(@Valid @RequestBody Coupon coupon) throws IOException, CouponSystemException {
		return new ResponseEntity<>(new ResponseDto(true, companyService.addCoupon(coupon).toString()), HttpStatus.CREATED);
	}

	@PutMapping("/coupon")
	public ResponseEntity<?> updateCoupon(@Valid @RequestBody Coupon coupon) throws IOException, CouponSystemException {
		companyService.updateCoupon(coupon);
		return new ResponseEntity<>(new ResponseDto(true, "coupon updated successfully"), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/coupon/{id}")
	public ResponseEntity<?> deleteCoupon(@PathVariable("id") Integer couponId)
			throws IOException, CouponSystemException {
		companyService.deleteCoupon(couponId);
		return new ResponseEntity<>(new ResponseDto(true, "coupon deleted successfully"), HttpStatus.ACCEPTED);
	}

	@GetMapping("/coupon")
	public ResponseEntity<?> getCompanyCoupons() {
		return new ResponseEntity<>(new CouponsList(companyService.getCompanyCoupons()), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/coupon/{id}")
	public ResponseEntity<?> getOneCoupon(@PathVariable("id") Integer couponId) {
		return new ResponseEntity<>(companyService.getOneCoupon(couponId), HttpStatus.ACCEPTED);
	}

	@GetMapping("/coupon/byCategory")
	public ResponseEntity<?> getCompanyCoupons(@RequestParam("category") Category category) {
		return new ResponseEntity<>(new CouponsList(companyService.getCompanyCoupons(category)), HttpStatus.ACCEPTED);
	}

	@GetMapping("/coupon/byMaxPrice")
	public ResponseEntity<?> getCompanyCoupons(@RequestParam("maxPrice") Double maxPrice) {
		return new ResponseEntity<>(new CouponsList(companyService.getCompanyCoupons(maxPrice)), HttpStatus.ACCEPTED);
	}
	
	@GetMapping
	public ResponseEntity<?> getCompanyDetails() {
		return new ResponseEntity<>(companyService.getCompanyDetails(), HttpStatus.ACCEPTED);
	}

}
