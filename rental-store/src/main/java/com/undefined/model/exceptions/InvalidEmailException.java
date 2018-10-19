package com.undefined.model.exceptions;

import com.undefined.error.ErrorResponse;

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