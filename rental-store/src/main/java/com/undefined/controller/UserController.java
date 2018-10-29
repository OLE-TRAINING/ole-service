package com.undefined.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.undefined.business.UserService;
import com.undefined.controller.validator.TokenRequestValidator;
import com.undefined.controller.validator.UserRequestValidator;
import com.undefined.model.entities.User;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users/{email}")
	public ResponseEntity<User> getUserThroughEmail(@PathVariable("email") String email) {
		UserRequestValidator.validateEmailRequest(email);
		return ResponseEntity.ok(userService.getUserThroughEmail(email));
	}
	
	@PostMapping("/users")
	public void createUser(@RequestBody User user) {
		UserRequestValidator.validateUserRequest(user);
		userService.createUser(user);
	}
	
	@PostMapping("/users/{email}/register/{token}")
	public void registerUser(@PathVariable("email") String email, @PathVariable("token") String token) {
		UserRequestValidator.validateEmailRequest(email);
		TokenRequestValidator.validateConfirmationTokenRequest(token);
		userService.registerUser(email, token);
	}
}