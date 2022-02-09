package com.ofir.coupons.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ofir.coupons.authorization.TokenManager;
import com.ofir.coupons.enums.ErrorMessage;
import com.ofir.coupons.utils.Utils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@Order(3)
public class AuthorizationFilter implements Filter {
	
	private final TokenManager tokenManager;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String url = httpRequest.getRequestURI();
		System.out.println(url);
		
		if (url.endsWith("login") || url.startsWith("/coupon-system/coupon")) {
			chain.doFilter(request, response);
            return;
        }
		
		String token = httpRequest.getHeader("authorization");
		System.out.println(token);
		
		if (!tokenManager.isTokenExists(token)) {
			httpResponse.sendError(401, ErrorMessage.TOKEN_NOT_FOUND.getDescription());
			return;
		}
			
		if (!tokenManager.isControllerAllowed(token, Utils.extractClientTypeFromUrl(url))) {
			httpResponse.sendError(401, ErrorMessage.TOKEN_NOT_AUTHORIZED.getDescription());
			return;
		}			
		
		chain.doFilter(httpRequest, response);
		
	}

}
