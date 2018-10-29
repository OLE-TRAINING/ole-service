package com.undefined.controller.validator;

import org.springframework.util.StringUtils;

import com.undefined.commons.error.ErrorMessage;
import com.undefined.commons.error.ErrorResponse;
import com.undefined.commons.exceptions.InvalidTokenException;
import com.undefined.commons.exceptions.NullFieldException;
import com.undefined.commons.validation.TokenValidator;

public class TokenRequestValidator {
	
	private TokenRequestValidator() {
		
	}

	public static void validateConfirmationTokenRequest(String token) {
		if (StringUtils.isEmpty(token)) {
			throw new NullFieldException(new ErrorResponse(ErrorMessage.Validation.NULL_OR_EMPTY_FIELD));
		}
		if (!TokenValidator.validateToken(token)) {
			throw new InvalidTokenException(new ErrorResponse(ErrorMessage.Validation.INVALID_TOKEN));
		}
	}
}
