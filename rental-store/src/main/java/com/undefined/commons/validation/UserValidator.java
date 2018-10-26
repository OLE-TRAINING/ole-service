package com.undefined.commons.validation;

import com.undefined.commons.error.ErrorMessage;
import com.undefined.commons.error.ErrorResponse;
import com.undefined.model.entities.User;

public class UserValidator {

	private UserValidator() {
		
	}
	
	private static boolean validateName(String name) {
		return name.length() <= 50 && name.matches("^[a-zA-Z]+( [a-zA-Z]+)*$");	
	}
	
	private static boolean validateUsername(String username) {
		return username.length() <= 15 && username.matches("[a-zA-Z0-9]+");
	}
	
	private static boolean validatePassword(String password) {
		return password.length() >= 6 && password.length() <= 10 && password.matches("^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$");
	}
	
	public static ErrorResponse validateUser(User user) {
		if (!EmailValidator.validateEmail(user.getEmail())) {
			return new ErrorResponse(ErrorMessage.Validation.INVALID_EMAIL);
		} else if (!validateName(user.getCompleteName())) {
			return new ErrorResponse(ErrorMessage.Validation.INVALID_COMPLETE_NAME);
		} else if (!validateUsername(user.getUsername())) {
			return new ErrorResponse(ErrorMessage.Validation.INVALID_USERNAME);
		} else if (!validatePassword(user.getPassword())) {
			return new ErrorResponse(ErrorMessage.Validation.INVALID_PASSWORD);
		} else {
			return null;
		}
	}
}