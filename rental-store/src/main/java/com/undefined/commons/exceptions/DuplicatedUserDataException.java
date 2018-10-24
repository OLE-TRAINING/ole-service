package com.undefined.commons.exceptions;

import com.undefined.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class DuplicatedUserDataException extends RuntimeException {
	
	private ErrorResponse errorResponse;

	public DuplicatedUserDataException() {
		
	}
	
	public DuplicatedUserDataException(String message) {
		super(message);
	}
	
	public DuplicatedUserDataException(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}
	
	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}
}