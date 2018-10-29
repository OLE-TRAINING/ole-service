package com.undefined.commons.validation;

import java.util.regex.Pattern;

import com.undefined.commons.error.ErrorMessage;
import com.undefined.commons.error.ErrorResponse;
import com.undefined.model.entities.User;

public class UserValidator {

	private UserValidator() {
		
	}
	
	public static boolean validateEmail(String email) {
		return Pattern.compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", Pattern.CASE_INSENSITIVE).matcher(email).matches();
	}
	
	public static boolean validateName(String name) {
		return name.length() <= 50 && name.matches("^[a-zA-Z]+( [a-zA-Z]+)*$");	
	}
	
	public static boolean validateUsername(String username) {
		return username.length() <= 15 && username.matches("[a-zA-Z0-9]+");
	}
	
	public static boolean validatePassword(String password) {
		return password.length() >= 6 && password.length() <= 10 && password.matches("^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$");
	}
	
	public static ErrorResponse validateUser(User user) {
		if (!validateEmail(user.getEmail())) {
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