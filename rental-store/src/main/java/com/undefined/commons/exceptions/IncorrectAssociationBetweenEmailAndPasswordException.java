package com.undefined.commons.exceptions;

import com.undefined.commons.error.ErrorResponse;

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