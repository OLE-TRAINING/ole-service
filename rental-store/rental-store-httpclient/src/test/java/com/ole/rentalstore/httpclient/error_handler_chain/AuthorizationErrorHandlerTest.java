package com.ole.rentalstore.httpclient.error_handler_chain;

import org.junit.Test;
import org.junit.Test.None;

import com.ole.rentalstore.httpclient.exceptions.HttpClientAuthorizationException;
import com.ole.rentalstore.httpclient.exceptions.error_handler_chain.ErrorHandler;
import com.ole.rentalstore.httpclient.exceptions.error_handler_chain.ErrorHandlerBuilder;

public class AuthorizationErrorHandlerTest {

	private ErrorHandler errorHandler = ErrorHandlerBuilder.buildErrorHandler();
	
	@Test(expected = HttpClientAuthorizationException.class)
	public void testHandlerErrorStatusCodeMatching() {
		errorHandler.handleError(401);
	}
	
	@Test(expected = None.class)
	public void testHandlerErrorStatusCodeNotMatching() {
		errorHandler.handleError(-1);
	}
}
