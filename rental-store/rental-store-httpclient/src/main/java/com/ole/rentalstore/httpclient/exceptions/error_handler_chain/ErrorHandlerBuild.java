package com.ole.rentalstore.httpclient.exceptions.error_handler_chain;

import org.springframework.stereotype.Component;

@Component
public class ErrorHandlerBuild {

	private ErrorHandler errorHandler;
	
	public ErrorHandlerBuild() {
		this.errorHandler = buildErrorHandler();
	}
	
	public static ErrorHandler buildErrorHandler() {
		ErrorHandler e1 = new AuthorizationErrorHandler();
		ErrorHandler e2 = new NotFoundErrorHandler();
		e1.setNextErrorHandler(e2);
		return e1;
	}
	
	public void handleError(int statusCode) {
		errorHandler.handleError(statusCode);
	}
}