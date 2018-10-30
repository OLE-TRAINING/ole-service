package com.undefined.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.undefined.business.UserService;
import com.undefined.controller.validator.TokenRequestValidator;
import com.undefined.controller.validator.UserRequestValidator;
import com.undefined.model.entities.User;
import com.undefined.model.entities.UserPasswordChange;

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
	
	@PostMapping("users/validate")
	public void authenticateUser(@RequestBody User user) {
		UserRequestValidator.validateEmailRequest(user.getEmail());
		UserRequestValidator.validatePasswordRequest(user.getPassword());
		userService.authenticateUser(user);
	}
	
	@PostMapping("users/confirm-data")
	public void validateEmailAndUsername(@RequestBody User user) {
		UserRequestValidator.validateEmailRequest(user.getEmail());
		UserRequestValidator.validateUsernameRequest(user.getUsername());
		userService.validateEmailAndUsername(user);
	}
	
	@PutMapping("users/password")
	public void changeUserPassword(@RequestBody UserPasswordChange user) {
		UserRequestValidator.validateUserPasswordChangeRequest(user);
		userService.changeUserPassword(user);
	}
}