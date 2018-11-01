package com.ole.rentalstore.commons.exceptions.conflict;

import com.ole.rentalstore.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class DuplicatedEmailException extends ConflitException {

	public DuplicatedEmailException() {
		
	}
	
	public DuplicatedEmailException(ErrorResponse errorResponse) {
		super(errorResponse);
	}
	
	public DuplicatedEmailException(String message) {
		super(message);
	}
}