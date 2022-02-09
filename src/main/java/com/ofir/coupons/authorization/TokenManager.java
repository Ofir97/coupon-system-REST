package com.ofir.coupons.authorization;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import com.ofir.coupons.enums.ClientType;
import com.ofir.coupons.services.ClientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenManager {
	
	private final Map<String, TokenInfo> tokens;
	
	public boolean isTokenExists(String token) {
		return tokens.containsKey(token);
	}
	
	/**
	 * LOGIN
	 * @param clientType is which client requests the token 
	 * @return token UUID as string
	 */
	public String createToken(ClientType clientType, ClientService service) {
		TokenInfo info = TokenInfo.generateInfo(clientType, service);
		tokens.put(info.getToken(), info);
		return info.getToken();
	}
	
	/**
	 * LOGOUT
	 * @param token to be removed
	 */
	public void removeToken(String token) {
		tokens.remove(token);
	}
	
	/**
	 * removes all expired tokens - tokens that have passed 30 minutes
	 */
	public void removeExpiredTokens() {
		tokens.entrySet().removeIf(entry -> new Date().after(DateUtils.addMinutes(entry.getValue().getCreationTime(), 30)));
	}
	
	/** 
	 * 
	 * @param token
	 * @param clientType according to the url
	 * @return true if user is authorized to use the specified controller, otherwise - false
	 */
	public boolean isControllerAllowed(String token, ClientType clientType) {
		return tokens.get(token).getClientType().equals(clientType);
	}
	
	/**
	 * 
	 * @param token
	 * @return
	 */
	public ClientService getService(String token) {
		return tokens.get(token).getService();
	}
	
	public Date getTokenExpirationTime(String token) {
		return DateUtils.addMinutes(tokens.get(token).getCreationTime(), 30);
	}
	

}
