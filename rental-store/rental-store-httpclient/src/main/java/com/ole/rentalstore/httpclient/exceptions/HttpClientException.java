package com.ole.rentalstore.httpclient.exceptions;

import com.ole.rentalstore.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class HttpClientException extends RuntimeException {

	private ErrorResponse errorResponse;
	
	public HttpClientException() {

	}

	public HttpClientException(String message) {
		super(message);
	}
	
	public HttpClientException(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}
	
	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}
}