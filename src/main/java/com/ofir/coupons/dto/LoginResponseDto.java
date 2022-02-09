package com.ofir.coupons.dto;

import java.util.Date;

import com.ofir.coupons.enums.ClientType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginResponseDto {
	
	private String token;
	private String name;
	private ClientType clientType;
	private Date tokenExpirationTime;

}
