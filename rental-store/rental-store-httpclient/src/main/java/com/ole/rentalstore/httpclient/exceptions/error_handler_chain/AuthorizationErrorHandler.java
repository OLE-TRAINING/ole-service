package com.ole.rentalstore.httpclient.exceptions.error_handler_chain;

import com.ole.rentalstore.httpclient.exceptions.HttpClientAuthorizationException;

public class AuthorizationErrorHandler extends ErrorHandler {

	@Override
	public void handleError(int statusCode) {
		if (statusCode == 401) {
			throw new HttpClientAuthorizationException();
		} else {
			if (this.getNextErrorHandler() != null) {
				this.getNextErrorHandler().handleError(statusCode);
			}
		}
	}
}