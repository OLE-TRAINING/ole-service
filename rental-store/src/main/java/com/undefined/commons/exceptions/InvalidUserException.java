package com.undefined.commons.exceptions;

import com.undefined.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class InvalidUserException extends BadRequestException {

	public InvalidUserException() {
		
	}
	
	public InvalidUserException(String message) {
		super(message);
	}
	
	public InvalidUserException(ErrorResponse errorResponse) {
		super(errorResponse);
	}
}
