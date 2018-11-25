package com.ole.rentalstore.httpclient.unirest.tmdb_api;

import org.apache.log4j.Logger;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.ole.rentalstore.httpclient.exceptions.HttpClientException;
import com.ole.rentalstore.httpclient.exceptions.error_handler_chain.ErrorHandler;
import com.ole.rentalstore.httpclient.exceptions.error_handler_chain.ErrorHandlerBuilder;
import com.ole.rentalstore.httpclient.unirest.UnirestCommonResources;

public abstract class TmdbCommonResources extends UnirestCommonResources{

	protected static final String BASE_URL = "https://api.themoviedb.org/3";
	protected static final String API_KEY = "?api_key=d272326e467344029e68e3c4ff0b4059";
	protected static final String LANGUAGE = "&language=pt-BR";
	protected static final String ADULT = "&adult=true";
	
	protected static ErrorHandler errorHandler = ErrorHandlerBuilder.buildErrorHandler();
	
	public static <T> T makeRequest(Class<T> responseClass, String url, Logger LOGGER) {
		T responseEntity = null;
		HttpResponse<T> httpResponse = null;
		try {
			httpResponse = Unirest.get(url).asObject(responseClass);
		} catch (UnirestException e) {
			LOGGER.error(e.getMessage());
			throw new HttpClientException(e.getMessage());
		}
		if (httpResponse.getStatus() == 200) {
			responseEntity = httpResponse.getBody();
		} else {
			errorHandler.handleError(httpResponse.getStatus());
		}
		return responseEntity;
	}
}