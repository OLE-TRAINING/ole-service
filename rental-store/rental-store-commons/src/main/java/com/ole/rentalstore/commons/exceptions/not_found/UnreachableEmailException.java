package com.ole.rentalstore.commons.exceptions.not_found;

import com.ole.rentalstore.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class UnreachableEmailException extends NotFoundException {

	public UnreachableEmailException() {
		
	}
	
	public UnreachableEmailException(ErrorResponse errorResponse) {
		super(errorResponse);
	}
	
	public UnreachableEmailException(String message) {
		super(message);
	}
}