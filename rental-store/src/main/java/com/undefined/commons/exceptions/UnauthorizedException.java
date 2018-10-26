package com.undefined.commons.exceptions;

import com.undefined.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class UnauthorizedException extends RuntimeException {

	private ErrorResponse errorResponse;

	public UnauthorizedException() {
		
	}
	
	public UnauthorizedException(String message) {
		super(message);
	}
	
	public UnauthorizedException(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}
	
	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}
}