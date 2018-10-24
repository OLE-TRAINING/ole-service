package com.undefined.commons.exceptions;

import com.undefined.commons.error.ErrorResponse;

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