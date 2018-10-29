package com.undefined.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.undefined.business.TokenService;
import com.undefined.controller.validator.UserRequestValidator;

@RestController
public class TokenController {
	
	@Autowired
	private TokenService tokenService;
	
	@PutMapping("/tokens/{email}")
	public void processToken(@PathVariable("email") String email) {
		UserRequestValidator.validateEmailRequest(email);
		tokenService.processToken(email);
	}
}