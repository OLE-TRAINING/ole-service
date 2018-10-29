package com.undefined.commons.exceptions;

import com.undefined.commons.error.ErrorResponse;

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