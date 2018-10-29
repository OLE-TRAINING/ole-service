package com.undefined.commons.exceptions;

import com.undefined.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class NullFieldException extends BadRequestException {

	private String fieldName;
	
	public NullFieldException() {
		
	}
	
	public NullFieldException(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public String getFieldName() {
		return this.fieldName;
	}
	
	public NullFieldException(ErrorResponse errorResponse) {
		super(errorResponse);
	}
}