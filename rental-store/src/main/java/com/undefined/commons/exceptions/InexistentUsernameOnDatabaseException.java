package com.undefined.commons.exceptions;

import com.undefined.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class InexistentUsernameOnDatabaseException extends NotFoundException {

	public InexistentUsernameOnDatabaseException() {
		
	}
	
	public InexistentUsernameOnDatabaseException(ErrorResponse errorResponse) {
		super(errorResponse);
	}
	
	public InexistentUsernameOnDatabaseException(String message) {
		super(message);
	}
}