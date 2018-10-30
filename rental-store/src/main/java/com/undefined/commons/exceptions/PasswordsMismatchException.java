package com.undefined.commons.exceptions;

import com.undefined.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class PasswordsMismatchException extends BadRequestException {

	public PasswordsMismatchException() {
		
	}
	
	public PasswordsMismatchException(String message) {
		super(message);
	}
	
	public PasswordsMismatchException(ErrorResponse errorResponse) {
		super(errorResponse);
	}
}