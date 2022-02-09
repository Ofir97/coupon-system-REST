package com.ofir.coupons.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofir.coupons.authorization.TokenManager;
import com.ofir.coupons.custom_exception.AuthorizationException;
import com.ofir.coupons.repositories.CompaniesRepository;
import com.ofir.coupons.repositories.CouponsRepository;
import com.ofir.coupons.repositories.CustomersRepository;

@Service
public abstract class ClientService {
	
	protected CompaniesRepository companiesRepository;
    protected CustomersRepository customersRepository;
    protected CouponsRepository couponsRepository;
    protected TokenManager tokenManager;
    
    @Autowired
    public ClientService(CompaniesRepository companiesRepository, 
    				     CustomersRepository customersRepository, 
    				     CouponsRepository couponsRepository,
    				     TokenManager tokenManager) {
    	this.companiesRepository = companiesRepository;
    	this.customersRepository = customersRepository;
    	this.couponsRepository = couponsRepository;
    	this.tokenManager = tokenManager;
    	
    }

    public abstract String login(String email, String password) throws AuthorizationException;
}
