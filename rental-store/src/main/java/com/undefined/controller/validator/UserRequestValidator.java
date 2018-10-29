package com.undefined.controller.validator;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.undefined.commons.error.ErrorMessage;
import com.undefined.commons.error.ErrorResponse;
import com.undefined.commons.exceptions.InvalidEmailException;
import com.undefined.commons.exceptions.InvalidPasswordException;
import com.undefined.commons.exceptions.InvalidUserException;
import com.undefined.commons.exceptions.InvalidUsernameException;
import com.undefined.commons.exceptions.NullFieldException;
import com.undefined.commons.validation.NullFieldValidator;
import com.undefined.commons.validation.UserValidator;
import com.undefined.model.entities.User;

public class UserRequestValidator {
	
	private static final Logger logger = Logger.getLogger(UserRequestValidator.class);
	
	private UserRequestValidator() {
		
	}

	public static void validateEmailRequest(String email) {
		if (StringUtils.isEmpty(email)) {
			throw new NullFieldException(new ErrorResponse(ErrorMessage.Validation.NULL_OR_EMPTY_FIELD));
		}
		if (!UserValidator.validateEmail(email)) {
			throw new InvalidEmailException(new ErrorResponse(ErrorMessage.Validation.INVALID_EMAIL));
		}
	}
	
	public static void validatePasswordRequest(String password) {
		if (StringUtils.isEmpty(password)) {
			throw new NullFieldException(new ErrorResponse(ErrorMessage.Validation.NULL_OR_EMPTY_FIELD));
		}
		if (!UserValidator.validatePassword(password)) {
			throw new InvalidPasswordException(new ErrorResponse(ErrorMessage.Validation.INVALID_PASSWORD));
		}
	}
	
	public static void validateUsernameRequest(String username) {
		if (StringUtils.isEmpty(username)) {
			throw new NullFieldException(new ErrorResponse(ErrorMessage.Validation.NULL_OR_EMPTY_FIELD));
		}
		if (!UserValidator.validateUsername(username)) {
			throw new InvalidUsernameException(new ErrorResponse(ErrorMessage.Validation.INVALID_USERNAME));
		}
	}
	
	public static void validateUserRequest(User user) {
		try {
			NullFieldValidator.checkForNullFields(user);
		} catch (NullFieldException ex) {
			if (!ex.getFieldName().equals("registrationStatus") && !ex.getFieldName().equals("confirmationToken")) {
				throw new NullFieldException(new ErrorResponse(ErrorMessage.Validation.NULL_OR_EMPTY_FIELD));
			}
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
		}
		ErrorResponse error = UserValidator.validateUser(user);
		if (error != null) {
			throw new InvalidUserException(error);
		}
	}
}