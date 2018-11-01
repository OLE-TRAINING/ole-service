package com.ole.rentalstore.commons.exceptions.unauthorized;

import com.ole.rentalstore.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class IncorrectAssociationBetweenEmailAndUsernameException extends UnauthorizedException {

	public IncorrectAssociationBetweenEmailAndUsernameException() {
		
	}
	
	public IncorrectAssociationBetweenEmailAndUsernameException(ErrorResponse errorResponse) {
		super(errorResponse);
	}
	
	public IncorrectAssociationBetweenEmailAndUsernameException(String message) {
		super(message);
	}
}