package com.undefined.commons.exceptions;

import com.undefined.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class InvalidUsernameException extends BadRequestException {

	public InvalidUsernameException() {
		
	}
	
	public InvalidUsernameException(String message) {
		super(message);
	}
	
	public InvalidUsernameException(ErrorResponse errorResponse) {
		super(errorResponse);
	}
}