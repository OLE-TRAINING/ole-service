package com.undefined.commons.validation;

import java.util.regex.Pattern;

import com.undefined.commons.error.ErrorMessage;
import com.undefined.commons.error.ErrorResponse;
import com.undefined.model.entities.User;
import com.undefined.model.entities.UserPasswordChange;

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
		} 
		if (!validateName(user.getCompleteName())) {
			return new ErrorResponse(ErrorMessage.Validation.INVALID_COMPLETE_NAME);
		} 
		if (!validateUsername(user.getUsername())) {
			return new ErrorResponse(ErrorMessage.Validation.INVALID_USERNAME);
		} 
		if (!validatePassword(user.getPassword())) {
			return new ErrorResponse(ErrorMessage.Validation.INVALID_PASSWORD);
		}
		return null;
	}
	
	public static ErrorResponse validateUserPasswordChange(UserPasswordChange user) {
		if (!validateEmail(user.getEmail())) {
			return new ErrorResponse(ErrorMessage.Validation.INVALID_EMAIL);
		}
		if (!validatePassword(user.getNewPassword())) {
			return new ErrorResponse(ErrorMessage.Validation.INVALID_PASSWORD);
		} 
		if (TokenValidator.validateToken(user.getConfirmationToken())) {
			return new ErrorResponse(ErrorMessage.Validation.INVALID_TOKEN);
		}
		if (user.getNewPassword().equals(user.getNewPasswordConfirmation())) {
			return new ErrorResponse(ErrorMessage.Validation.PASSWORDS_MISMATCH);
		}
		return null;
	}
}