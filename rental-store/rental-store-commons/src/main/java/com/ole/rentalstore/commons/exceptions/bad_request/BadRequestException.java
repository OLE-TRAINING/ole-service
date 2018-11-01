package com.ole.rentalstore.commons.exceptions.bad_request;

import com.ole.rentalstore.commons.error.ErrorResponse;

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