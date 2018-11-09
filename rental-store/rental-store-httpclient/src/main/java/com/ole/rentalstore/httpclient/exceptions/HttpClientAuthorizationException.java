package com.ole.rentalstore.httpclient.exceptions;

import com.ole.rentalstore.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class HttpClientAuthorizationException extends HttpClientException {

	public HttpClientAuthorizationException() {
		
	}
	
	public HttpClientAuthorizationException(String message) {
		super(message);
	}
	
	public HttpClientAuthorizationException(ErrorResponse errorResponse) {
		super(errorResponse);
	}
}