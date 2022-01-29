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
import org.springframework.web.bind.annotation.RestController;

import com.ofir.coupons.beans.CompaniesList;
import com.ofir.coupons.beans.Company;
import com.ofir.coupons.beans.Customer;
import com.ofir.coupons.beans.CustomersList;
import com.ofir.coupons.beans.ResponseDto;
import com.ofir.coupons.beans.LoginDetails;
import com.ofir.coupons.custom_exception.CouponSystemException;
import com.ofir.coupons.services.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/coupon-system/admin")
@RequiredArgsConstructor
@CrossOrigin
public class AdminController extends ClientController {

	private final AdminService adminService;
	
	@Override
	@PostMapping("/login")
	public boolean login(@Valid @RequestBody LoginDetails loginDetails) {
		return adminService.login(loginDetails.getEmail(), loginDetails.getPassword());
	}
	
	@PostMapping("/company")
	public ResponseEntity<?> addCompany(@Valid @RequestBody Company company) throws IOException, CouponSystemException {
		return new ResponseEntity<>(new ResponseDto(true, adminService.addCompany(company).toString()), HttpStatus.CREATED);
	}
	
	@PutMapping("/company")
	public ResponseEntity<?> updateCompany(@Valid @RequestBody Company company) throws IOException, CouponSystemException {
		adminService.updateCompany(company);
		return new ResponseEntity<>(new ResponseDto(true, "company updated successfully"), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/company/{id}")
	public ResponseEntity<?> deleteCompany(@PathVariable("id") Integer id) throws IOException, CouponSystemException {
		adminService.deleteCompany(id);
		return new ResponseEntity<>(new ResponseDto(true, "company deleted successfully"), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/company")
	public ResponseEntity<?> getAllCompanies() {
		return new ResponseEntity<>(new CompaniesList(adminService.getAllCompanies()), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/company/{id}")
	public ResponseEntity<?> getCompanyById(@PathVariable("id") Integer id) throws CouponSystemException {
		Company company = adminService.getOneCompany(id);
		return new ResponseEntity<>(company, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/customer")
	public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer customer) throws IOException, CouponSystemException {
		return new ResponseEntity<>(new ResponseDto(true, adminService.addCustomer(customer).toString()), HttpStatus.CREATED);
	}
	
	@PutMapping("/customer")
	public ResponseEntity<?> updateCustomer(@Valid @RequestBody Customer customer) throws IOException, CouponSystemException {
		adminService.updateCustomer(customer);
		return new ResponseEntity<>(new ResponseDto(true, "customer updated successfully"), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") Integer id) throws IOException, CouponSystemException {
		adminService.deleteCustomer(id);
		return new ResponseEntity<>(new ResponseDto(true, "customer deleted successfully"), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/customer")
	public ResponseEntity<?> getAllCustomers() {
		return new ResponseEntity<>(new CustomersList(adminService.getAllCustomers()), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable("id") Integer id) throws CouponSystemException {
		Customer customer = adminService.getOneCustomer(id);
		return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
	}

}
