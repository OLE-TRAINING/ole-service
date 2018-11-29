package com.ole.rentalstore.commons.validation;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.ole.rentalstore.commons.dto.UserDTO;
import com.ole.rentalstore.commons.dto.UserForPasswordChangeDTO;
import com.ole.rentalstore.commons.error.ErrorMessage;
import com.ole.rentalstore.commons.error.ErrorResponse;

public class UserValidator {

	private UserValidator() {}
	
	public static boolean validateEmail(String email) {
		try {
			new InternetAddress(email).validate();
			return true;
		} catch (AddressException e) {
			return false;
		}
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
	
	public static ErrorResponse validateUser(UserDTO user) {
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
	
	public static ErrorResponse validateUserPasswordChange(UserForPasswordChangeDTO user) {
		if (!validateEmail(user.getEmail())) {
			return new ErrorResponse(ErrorMessage.Validation.INVALID_EMAIL);
		}
		if (!validatePassword(user.getNewPassword())) {
			return new ErrorResponse(ErrorMessage.Validation.INVALID_PASSWORD);
		} 
		if (!TokenValidator.validateToken(user.getConfirmationToken())) {
			return new ErrorResponse(ErrorMessage.Validation.INVALID_TOKEN);
		}
		if (!user.getNewPassword().equals(user.getNewPasswordConfirmation())) {
			return new ErrorResponse(ErrorMessage.Validation.PASSWORDS_MISMATCH);
		}
		return null;
	}
}