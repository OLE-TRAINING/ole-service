package com.ole.rentalstore.commons.exceptions.not_found.returning_id;

import com.ole.rentalstore.commons.error.ErrorResponse;
import com.ole.rentalstore.commons.exceptions.not_found.returning_id.NotFoundReturningSearchIdException;

@SuppressWarnings("serial")
public class MovieNotFoundByNameException extends NotFoundReturningSearchIdException {
	
	public MovieNotFoundByNameException() {
		
	}
	
	public MovieNotFoundByNameException(ErrorResponse errorResponse) {
		super(errorResponse);
	}
	
	public MovieNotFoundByNameException(ErrorResponse errorResponse, String searchId) {
		super(errorResponse, searchId);
	}
	
	public MovieNotFoundByNameException(String message) {
		super(message);
	}
}