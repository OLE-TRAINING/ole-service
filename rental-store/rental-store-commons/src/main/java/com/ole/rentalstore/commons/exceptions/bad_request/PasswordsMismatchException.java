package com.ole.rentalstore.commons.exceptions.bad_request;

import com.ole.rentalstore.commons.error.ErrorResponse;

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