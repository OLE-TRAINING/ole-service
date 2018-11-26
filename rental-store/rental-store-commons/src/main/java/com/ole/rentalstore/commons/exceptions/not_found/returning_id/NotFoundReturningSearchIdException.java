package com.ole.rentalstore.commons.exceptions.not_found.returning_id;

import com.ole.rentalstore.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class NotFoundReturningSearchIdException extends RuntimeException {

	private ErrorResponse errorResponse;
	private String searchId;
	
	public NotFoundReturningSearchIdException() {

	}
	
	public NotFoundReturningSearchIdException(String searchId) {
		this.searchId = searchId;
	}
	
	public NotFoundReturningSearchIdException(ErrorResponse errorResponse, String searchId) {
		this.errorResponse = errorResponse;
		this.searchId = searchId;
	}
	
	public NotFoundReturningSearchIdException(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}
	
	public String getSearchId() {
		return searchId;
	}
	
	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}
}