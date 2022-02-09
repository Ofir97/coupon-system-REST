package com.ofir.coupons.authorization;

import java.util.Date;
import java.util.UUID;

import com.ofir.coupons.enums.ClientType;
import com.ofir.coupons.services.ClientService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TokenInfo {
	
	private String token;
	private Date creationTime;
	private ClientType clientType;
	private ClientService service;
	
	public static TokenInfo generateInfo(ClientType clientType, ClientService service) {
		return TokenInfo.builder()
				.token(UUID.randomUUID().toString())
				.creationTime(new Date())
				.clientType(clientType)
				.service(service)
				.build();
	}

}
