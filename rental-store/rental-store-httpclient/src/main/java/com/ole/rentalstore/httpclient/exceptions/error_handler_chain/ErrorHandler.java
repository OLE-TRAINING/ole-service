package com.ole.rentalstore.httpclient.exceptions.error_handler_chain;

public abstract class ErrorHandler {

	private ErrorHandler nextErrorHandler;

	public ErrorHandler getNextErrorHandler() {
		return nextErrorHandler;
	}

	public void setNextErrorHandler(ErrorHandler nextErrorHandler) {
		this.nextErrorHandler = nextErrorHandler;
	}
	
	public abstract void handleError(int statusCode);
}