package com.ole.rentalstore.commons.exceptions.unauthorized;

import com.ole.rentalstore.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class IncorrectAssociationBetweenEmailAndPasswordException extends UnauthorizedException {

	public IncorrectAssociationBetweenEmailAndPasswordException() {
		
	}
	
	public IncorrectAssociationBetweenEmailAndPasswordException(ErrorResponse errorResponse) {
		super(errorResponse);
	}
	
	public IncorrectAssociationBetweenEmailAndPasswordException(String message) {
		super(message);
	}
}