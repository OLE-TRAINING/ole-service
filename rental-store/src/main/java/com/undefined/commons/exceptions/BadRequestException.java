package com.undefined.commons.exceptions;

import com.undefined.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class BadRequestException extends RuntimeException {

	private ErrorResponse errorResponse;
	
	public BadRequestException() {

	}

	public BadRequestException(String message) {
		super(message);
	}
	
	public BadRequestException(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}
	
	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}
}