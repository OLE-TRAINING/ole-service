package com.ole.rentalstore.httpclient.unirest;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mashape.unirest.http.HttpMethod;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequest;
import com.ole.rentalstore.httpclient.exceptions.HttpClientException;
import com.ole.rentalstore.httpclient.exceptions.error_handler_chain.ErrorHandler;
import com.ole.rentalstore.httpclient.exceptions.error_handler_chain.ErrorHandlerBuilder;
import com.ole.rentalstore.httpclient.unirest.configuration.UnirestConfiguration;

public class UnirestCommonResources {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UnirestCommonResources.class);
	
	private ErrorHandler errorHandler = ErrorHandlerBuilder.buildErrorHandler();
	
	static {
		Unirest.setObjectMapper(UnirestConfiguration.getObjectMapperConfigurated());
	}
	
	protected HttpRequest buildRequest(HttpMethod method, String url, Map<String, Object> params, Map<String, Object> headers) {
		HttpRequest request = new HttpRequest(method, url);
		if (headers != null) {
			for (Map.Entry<String, Object> entry : headers.entrySet()) {
				request.header(entry.getKey(), entry.getValue() != null ? entry.getValue().toString() : null);
			}
		}
		request.queryString(params);
		return request;
	}
	
	protected <T> T handleRequest(HttpRequest request, Class<T> resultClazz) {
		
		try {
			HttpResponse<T> httpResponse = request.asObject(resultClazz);
			if (httpResponse.getStatus() == 200) {
				return httpResponse.getBody();
			}
			errorHandler.handleError(httpResponse.getStatus());
			return null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new HttpClientException(e.getMessage());
		}
	}
}