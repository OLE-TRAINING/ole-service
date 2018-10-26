package com.undefined.commons.exceptions;

import com.undefined.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class InexistentEmailOnDatabaseException extends UnauthorizedException {

	public InexistentEmailOnDatabaseException() {
		
	}
	
	public InexistentEmailOnDatabaseException(ErrorResponse errorResponse) {
		super(errorResponse);
	}
	
	public InexistentEmailOnDatabaseException(String message) {
		super(message);
	}
}