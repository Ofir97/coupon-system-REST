package com.ofir.coupons.beans;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoginDetails {

	@NotEmpty(message = "email cannot be empty.")
	@Email(message = "email is invalid.")
	private String email;
	
	@NotEmpty(message = "password cannot be empty.")
	@Size(min = 5, message = "password should have at least 5 characters.")
	private String password;
	
	
}
