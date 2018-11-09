package com.ole.rentalstore.httpclient.exceptions;

import com.ole.rentalstore.commons.error.ErrorResponse;

@SuppressWarnings("serial")
public class HttpClientNotFoundException extends HttpClientException {

	public HttpClientNotFoundException() {
		
	}
	
	public HttpClientNotFoundException(String message) {
		super(message);
	}
	
	public HttpClientNotFoundException(ErrorResponse errorResponse) {
		super(errorResponse);
	}
}