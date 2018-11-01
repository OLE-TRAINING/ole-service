package com.ole.rentalstore.commons.exceptions.bad_request;

import com.ole.rentalstore.commons.error.ErrorResponse;

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