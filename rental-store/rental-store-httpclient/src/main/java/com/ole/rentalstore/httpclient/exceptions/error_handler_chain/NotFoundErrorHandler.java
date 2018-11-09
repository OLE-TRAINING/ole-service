package com.ole.rentalstore.httpclient.exceptions.error_handler_chain;

import com.ole.rentalstore.httpclient.exceptions.HttpClientNotFoundException;

public class NotFoundErrorHandler extends ErrorHandler {

	@Override
	public void handleError(int statusCode) {
		if (statusCode == 404) {
			throw new HttpClientNotFoundException();
		} else {
			if (this.getNextErrorHandler() != null) {
				this.getNextErrorHandler().handleError(statusCode);
			}
		}
	}
}