package com.ofir.coupons.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ofir.coupons.beans.CouponsList;
import com.ofir.coupons.enums.Category;
import com.ofir.coupons.services.CouponService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/coupon-system/coupon")
@CrossOrigin
@RequiredArgsConstructor
public class CouponsController {

	private final CouponService couponService;
	
	@GetMapping
	public ResponseEntity<?> getAllCoupons() {
		return new ResponseEntity<>(new CouponsList(couponService.getAllCoupons()), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/byCategory")
	public ResponseEntity<?> getCoupons(@RequestParam("category") Category category) {
		return new ResponseEntity<>(new CouponsList(couponService.getCouponsByCategory(category)), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/byMaxPrice")
	public ResponseEntity<?> getCoupons(@RequestParam("maxPrice") Double maxPrice) {
		return new ResponseEntity<>(new CouponsList(couponService.getCouponsByMaxPrice(maxPrice)), HttpStatus.ACCEPTED);
	}
}
