package com.undefined.commons.exceptions;

import com.undefined.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class InvalidPasswordException extends BadRequestException {

	public InvalidPasswordException() {
		
	}
	
	public InvalidPasswordException(String message) {
		super(message);
	}
	
	public InvalidPasswordException(ErrorResponse errorResponse) {
		super(errorResponse);
	}
}