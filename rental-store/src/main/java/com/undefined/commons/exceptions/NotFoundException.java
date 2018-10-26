package com.undefined.commons.exceptions;

import com.undefined.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {

	private ErrorResponse errorResponse;
	
	public NotFoundException() {

	}

	public NotFoundException(String message) {
		super(message);
	}
	
	public NotFoundException(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}
	
	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}
}
