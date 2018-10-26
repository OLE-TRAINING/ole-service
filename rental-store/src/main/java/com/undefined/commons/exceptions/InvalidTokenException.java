package com.undefined.commons.exceptions;

import com.undefined.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class InvalidTokenException extends BadRequestException {

	public InvalidTokenException() {
		
	}
	
	public InvalidTokenException(String message) {
		super(message);
	}
	
	public InvalidTokenException(ErrorResponse errorResponse) {
		super(errorResponse);
	}
}