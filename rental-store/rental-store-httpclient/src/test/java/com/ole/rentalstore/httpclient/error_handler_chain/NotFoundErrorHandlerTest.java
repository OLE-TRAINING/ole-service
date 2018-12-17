package com.ole.rentalstore.httpclient.error_handler_chain;

import org.junit.Test;
import org.junit.Test.None;

import com.ole.rentalstore.httpclient.exceptions.HttpClientNotFoundException;
import com.ole.rentalstore.httpclient.exceptions.error_handler_chain.ErrorHandler;
import com.ole.rentalstore.httpclient.exceptions.error_handler_chain.ErrorHandlerBuilder;

public class NotFoundErrorHandlerTest {

	private ErrorHandler errorHandler = ErrorHandlerBuilder.buildErrorHandler();
	
	@Test(expected = HttpClientNotFoundException.class)
	public void testHandlerErrorStatusCodeMatching() {
		errorHandler.handleError(404);
	}
	
	@Test(expected = None.class)
	public void testHandlerErrorStatusCodeNotMatching() {
		errorHandler.handleError(-1);
	}
}
