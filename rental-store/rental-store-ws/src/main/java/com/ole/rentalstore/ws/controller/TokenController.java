package com.ole.rentalstore.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ole.rentalstore.business.service.TokenService;
import com.ole.rentalstore.ws.validation.UserRequestValidator;

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
