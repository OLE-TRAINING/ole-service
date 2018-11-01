package com.ole.rentalstore.commons.exceptions.not_found;

import com.ole.rentalstore.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class InexistentEmailOnDatabaseException extends NotFoundException {

	public InexistentEmailOnDatabaseException() {
		
	}
	
	public InexistentEmailOnDatabaseException(ErrorResponse errorResponse) {
		super(errorResponse);
	}
	
	public InexistentEmailOnDatabaseException(String message) {
		super(message);
	}
}