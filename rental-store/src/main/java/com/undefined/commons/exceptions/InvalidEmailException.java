package com.undefined.commons.exceptions;

import com.undefined.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class InvalidEmailException extends BadRequestException {

	public InvalidEmailException() {
			
	}
	
	public InvalidEmailException(String message) {
		super(message);
	}
	
	public InvalidEmailException(ErrorResponse errorResponse) {
		super(errorResponse);
	}
}