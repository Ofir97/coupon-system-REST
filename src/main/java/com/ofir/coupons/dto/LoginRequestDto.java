package com.ofir.coupons.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequestDto {

	@NotEmpty(message = "email cannot be empty.")
	@Email(message = "email is invalid.")
	private String email;
	
	@NotEmpty(message = "password cannot be empty.")
	@Size(min = 5, message = "password should have at least 5 characters.")
	private String password;
	
	
}
