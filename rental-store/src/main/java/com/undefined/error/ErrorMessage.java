package com.undefined.error;

public class ErrorMessage {
	
	private ErrorMessage () {}
	
	public static final String UNEXPECTED_ERROR = "error.unexpected";
	
	public static class Validation {
		
		private Validation() {}
		
		public static final String INVALID_EMAIL = "error.invalid.email";
		public static final String NULL_OR_EMPTY_FIELD = "error.invalid.empty";
	}
	
	public static class Resource {
		
		private Resource() {}
	}
}
