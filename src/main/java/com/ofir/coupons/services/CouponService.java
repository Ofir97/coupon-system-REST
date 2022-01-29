package com.ofir.coupons.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ofir.coupons.beans.Coupon;
import com.ofir.coupons.enums.Category;
import com.ofir.coupons.repositories.CouponsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponService {

	private final CouponsRepository couponsRepo;
	
	public List<Coupon> getAllCoupons() {
		return couponsRepo.findAll();
	}
	
	public List<Coupon> getCouponsByCategory(Category category) {
		return couponsRepo.findByCategory(category);
	}
	
	public List<Coupon> getCouponsByMaxPrice(Double price) {
		return couponsRepo.findByPriceLessThanEqual(price);
	}
}
