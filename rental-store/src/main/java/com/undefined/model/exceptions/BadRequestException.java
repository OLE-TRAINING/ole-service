package com.undefined.model.exceptions;

import com.undefined.error.ErrorResponse;

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