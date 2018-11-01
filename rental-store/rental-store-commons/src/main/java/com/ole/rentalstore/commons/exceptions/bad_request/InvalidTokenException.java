package com.ole.rentalstore.commons.exceptions.bad_request;

import com.ole.rentalstore.commons.error.ErrorResponse;

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