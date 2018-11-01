package com.ole.rentalstore.commons.exceptions.conflict;

import com.ole.rentalstore.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class DuplicatedUsernameException extends ConflitException {

	public DuplicatedUsernameException() {
		
	}
	
	public DuplicatedUsernameException(ErrorResponse errorResponse) {
		super(errorResponse);
	}
	
	public DuplicatedUsernameException(String message) {
		super(message);
	}
}