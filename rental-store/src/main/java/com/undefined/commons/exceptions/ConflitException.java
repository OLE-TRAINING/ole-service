package com.undefined.commons.exceptions;

import com.undefined.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class ConflitException extends RuntimeException {

	private ErrorResponse errorResponse;

	public ConflitException() {
		
	}
	
	public ConflitException(String message) {
		super(message);
	}
	
	public ConflitException(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}
	
	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}
}
