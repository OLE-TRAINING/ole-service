package com.undefined.commons.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undefined.business.UserService;
import com.undefined.model.entities.User;

@Component
public class UserDataValidator {
	
	@Autowired
	private UserService userService;

	private UserDataValidator() {
		
	}
	
	public boolean emailAlreadyExists(String email) {
		Optional<User> user = userService.findUserByEmail(email);
		return user.isPresent();
	}
	
	public boolean usernameAlreadyExists(String username) {
		Optional<User> user = userService.findUserByUsername(username);
		return user.isPresent();
	}
}