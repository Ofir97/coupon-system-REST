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
import org.springframework.web.bind.annotation.RestController;

import com.ofir.coupons.beans.CompaniesList;
import com.ofir.coupons.beans.Company;
import com.ofir.coupons.beans.Customer;
import com.ofir.coupons.beans.CustomersList;
import com.ofir.coupons.custom_exception.AuthorizationException;
import com.ofir.coupons.custom_exception.CouponSystemException;
import com.ofir.coupons.dto.LoginRequestDto;
import com.ofir.coupons.dto.LoginResponseDto;
import com.ofir.coupons.dto.ResponseDto;
import com.ofir.coupons.enums.ClientType;
import com.ofir.coupons.services.AdminService;
import com.ofir.coupons.utils.LoginManager;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/coupon-system/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class AdminController extends ClientController {

	private final LoginManager loginManager;

	@Override
	@PostMapping("/login")
	public LoginResponseDto login(@Valid @RequestBody LoginRequestDto loginRequest) throws AuthorizationException {
		return loginManager.login(loginRequest.getEmail(), loginRequest.getPassword(), ClientType.ADMIN);
	}

	@Override
	@DeleteMapping("/logout")
	public ResponseEntity<?> logout(@RequestHeader("authorization") String token) {
		loginManager.logout(token);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/company")
	public ResponseEntity<?> addCompany(@RequestHeader("authorization") String token,
			@Valid @RequestBody Company company) throws IOException, CouponSystemException {
		AdminService service = (AdminService) loginManager.getService(token);
		return new ResponseEntity<>(new ResponseDto(true, service.addCompany(company).toString()), HttpStatus.CREATED);
	}

	@PutMapping("/company")
	public ResponseEntity<?> updateCompany(@RequestHeader("authorization") String token,
			@Valid @RequestBody Company company) throws IOException, CouponSystemException {
		AdminService service = (AdminService) loginManager.getService(token);
		service.updateCompany(company);
		return new ResponseEntity<>(new ResponseDto(true, "company updated successfully"), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/company/{id}")
	public ResponseEntity<?> deleteCompany(@RequestHeader("authorization") String token, @PathVariable("id") Integer id)
			throws IOException, CouponSystemException {
		AdminService service = (AdminService) loginManager.getService(token);
		service.deleteCompany(id);
		return new ResponseEntity<>(new ResponseDto(true, "company deleted successfully"), HttpStatus.ACCEPTED);
	}

	@GetMapping("/company")
	public ResponseEntity<?> getAllCompanies(@RequestHeader("authorization") String token) {
		System.out.println(token);
		AdminService service = (AdminService) loginManager.getService(token);
		return new ResponseEntity<>(new CompaniesList(service.getAllCompanies()), HttpStatus.ACCEPTED);
	}

	@GetMapping("/company/{id}")
	public ResponseEntity<?> getCompanyById(@RequestHeader("authorization") String token,
			@PathVariable("id") Integer id) throws CouponSystemException {
		AdminService service = (AdminService) loginManager.getService(token);
		Company company = service.getOneCompany(id);
		return new ResponseEntity<>(company, HttpStatus.ACCEPTED);
	}

	@PostMapping("/customer")
	public ResponseEntity<?> addCustomer(@RequestHeader("authorization") String token,
			@Valid @RequestBody Customer customer) throws IOException, CouponSystemException {
		AdminService service = (AdminService) loginManager.getService(token);
		return new ResponseEntity<>(new ResponseDto(true, service.addCustomer(customer).toString()),
				HttpStatus.CREATED);
	}

	@PutMapping("/customer")
	public ResponseEntity<?> updateCustomer(@RequestHeader("authorization") String token,
			@Valid @RequestBody Customer customer) throws IOException, CouponSystemException {
		AdminService service = (AdminService) loginManager.getService(token);
		service.updateCustomer(customer);
		return new ResponseEntity<>(new ResponseDto(true, "customer updated successfully"), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("customer/{id}")
	public ResponseEntity<?> deleteCustomer(@RequestHeader("authorization") String token,
			@PathVariable("id") Integer id) throws IOException, CouponSystemException {
		AdminService service = (AdminService) loginManager.getService(token);
		service.deleteCustomer(id);
		return new ResponseEntity<>(new ResponseDto(true, "customer deleted successfully"), HttpStatus.ACCEPTED);
	}

	@GetMapping("/customer")
	public ResponseEntity<?> getAllCustomers(@RequestHeader("authorization") String token) {
		AdminService service = (AdminService) loginManager.getService(token);
		return new ResponseEntity<>(new CustomersList(service.getAllCustomers()), HttpStatus.ACCEPTED);
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<?> getCustomerById(@RequestHeader("authorization") String token,
			@PathVariable("id") Integer id) throws CouponSystemException {
		AdminService service = (AdminService) loginManager.getService(token);
		Customer customer = service.getOneCustomer(id);
		return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
	}

}
