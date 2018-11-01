package com.ole.rentalstore.commons.exceptions.not_found;

import com.ole.rentalstore.commons.error.ErrorResponse;

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