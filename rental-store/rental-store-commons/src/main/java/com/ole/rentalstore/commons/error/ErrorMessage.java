package com.ole.rentalstore.commons.error;

public class ErrorMessage {

	private ErrorMessage () {}
	
	public static final String UNEXPECTED_ERROR = "error.unexpected";
	
	public static class Validation {
		
		private Validation() {}
		
		public static final String INVALID_EMAIL = "error.invalid.email";
		public static final String INVALID_COMPLETE_NAME = "error.invalid.name";
		public static final String INVALID_PASSWORD = "error.invalid.password";
		public static final String INVALID_USERNAME = "error.invalid.username";
		public static final String INVALID_TOKEN = "error.invalid.token";
		
		public static final String NULL_OR_EMPTY_FIELD = "error.invalid.empty";
		
		public static final String PASSWORDS_MISMATCH = "error.invalid.password.mismatch";
	}
	
	public static class Resource {
		
		private Resource() {}
		
		public static final String DUPLICATED_EMAIL = "error.resource.email.duplicated";
		public static final String DUPLICATED_USERNAME = "error.resource.username.duplicated";
	}
	
	public static class Inexistent {
		
		private Inexistent() {}
		public static final String INEXISTENT_EMAIL = "error.inexistent.email";
		public static final String INEXISTENT_USERNAME = "error.inexistent.username";
		public static final String INEXISTENT_MOVIE_NAME = "error.inexistent.movie.name";
		public static final String UNREACHABLE_EMAIL = "error.inexistent.email.smtp";
	}
	
	public static class Unauthenticated {
		
		private Unauthenticated() {}
		public static final String INCORRECT_TOKEN = "error.unauthorized.token";
		public static final String INCORRECT_PASSWORD = "error.unauthorized.password";
		public static final String INCORRECT_USERNAME = "error.unauthorized.username";
	}
}
