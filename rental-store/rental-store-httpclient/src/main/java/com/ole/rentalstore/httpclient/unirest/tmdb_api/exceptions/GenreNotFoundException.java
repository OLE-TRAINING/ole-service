package com.ole.rentalstore.httpclient.unirest.tmdb_api.exceptions;

import com.ole.rentalstore.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class GenreNotFoundException extends RuntimeException {

	private ErrorResponse errorResponse;
	
	public GenreNotFoundException() {

	}

	public GenreNotFoundException(String message) {
		super(message);
	}
	
	public GenreNotFoundException(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}
	
	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}
}