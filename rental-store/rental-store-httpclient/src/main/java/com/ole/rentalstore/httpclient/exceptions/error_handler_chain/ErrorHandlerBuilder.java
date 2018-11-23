package com.ole.rentalstore.httpclient.exceptions.error_handler_chain;

public class ErrorHandlerBuilder {
	
	private ErrorHandlerBuilder() {
		
	}
	
	public static ErrorHandler buildErrorHandler() {
		ErrorHandler e1 = new AuthorizationErrorHandler();
		ErrorHandler e2 = new NotFoundErrorHandler();
		e1.setNextErrorHandler(e2);
		return e1;
	}
}