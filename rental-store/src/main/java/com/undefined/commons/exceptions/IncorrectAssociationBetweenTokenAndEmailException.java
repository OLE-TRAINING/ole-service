package com.undefined.commons.exceptions;

import com.undefined.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class IncorrectAssociationBetweenTokenAndEmailException extends UnauthorizedException {

	public IncorrectAssociationBetweenTokenAndEmailException() {
		
	}
	
	public IncorrectAssociationBetweenTokenAndEmailException(ErrorResponse errorResponse) {
		super(errorResponse);
	}
	
	public IncorrectAssociationBetweenTokenAndEmailException(String message) {
		super(message);
	}
}