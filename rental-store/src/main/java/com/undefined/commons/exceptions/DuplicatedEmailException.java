package com.undefined.commons.exceptions;

import com.undefined.commons.error.ErrorResponse;

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