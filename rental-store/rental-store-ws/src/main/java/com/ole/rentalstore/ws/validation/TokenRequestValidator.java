package com.ole.rentalstore.ws.validation;

import org.springframework.util.StringUtils;

import com.ole.rentalstore.commons.error.ErrorMessage;
import com.ole.rentalstore.commons.error.ErrorResponse;
import com.ole.rentalstore.commons.exceptions.bad_request.InvalidTokenException;
import com.ole.rentalstore.commons.exceptions.bad_request.NullFieldException;
import com.ole.rentalstore.commons.validation.TokenValidator;

public class TokenRequestValidator {
	
	private TokenRequestValidator() {}

	public static void validateConfirmationTokenRequest(String token) {
		if (StringUtils.isEmpty(token)) {
			throw new NullFieldException(new ErrorResponse(ErrorMessage.Validation.NULL_OR_EMPTY_FIELD));
		}
		if (!TokenValidator.validateToken(token)) {
			throw new InvalidTokenException(new ErrorResponse(ErrorMessage.Validation.INVALID_TOKEN));
		}
	}
}