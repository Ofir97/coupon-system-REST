package com.ofir.coupons.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.ofir.coupons.authorization.TokenInfo;
import com.ofir.coupons.authorization.TokenManager;
import com.ofir.coupons.custom_exception.AuthorizationException;
import com.ofir.coupons.dto.LoginResponseDto;
import com.ofir.coupons.enums.ClientType;
import com.ofir.coupons.services.AdminService;
import com.ofir.coupons.services.ClientService;
import com.ofir.coupons.services.CompanyService;
import com.ofir.coupons.services.CustomerService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginManager {

	private final ApplicationContext context;
	private final TokenManager tokenManager;

	/**
	 * @param 	email is the email of the admin/company/customer that wants to log in
	 * @param 	password is the password of the admin/company/customer that wants to log in
	 * @param 	clientType is the type of client that wants to login(types are: admin, company and customer)
	 * @return	token if login is successful, otherwise - throws AuthorizationException
	 * @throws  AuthorizationException if login failed
	 */
	public LoginResponseDto login(String email, String password, ClientType clientType) throws AuthorizationException {
		ClientService clientService = null; 
		String clientName = "";
		
		switch (clientType) {
		case ADMIN:
			clientService = context.getBean(AdminService.class);
			break;
		case COMPANY:
			clientService = context.getBean(CompanyService.class);
			break;
		case CUSTOMER:
			clientService = context.getBean(CustomerService.class);
		}

		if (clientService != null) {
			clientName = clientService.login(email, password);
		}
		
		String token = tokenManager.createToken(clientType, clientService);
		
		return LoginResponseDto.builder()
        		.token(token)
        		.name(clientName)
        		.clientType(clientType)
        		.tokenExpirationTime(tokenManager.getTokenExpirationTime(token))
        		.build();
	}
	
	public void logout(String token) {
		tokenManager.removeToken(token);
	}
	
	public ClientService getService(String token) {
		return tokenManager.getService(token);
	}
}
