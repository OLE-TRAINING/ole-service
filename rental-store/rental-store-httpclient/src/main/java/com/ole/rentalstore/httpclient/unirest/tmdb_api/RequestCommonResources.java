package com.ole.rentalstore.httpclient.unirest.tmdb_api;

import com.mashape.unirest.http.Unirest;
import com.ole.rentalstore.httpclient.exceptions.error_handler_chain.ErrorHandler;
import com.ole.rentalstore.httpclient.exceptions.error_handler_chain.ErrorHandlerBuild;
import com.ole.rentalstore.httpclient.unirest.configuration.UnirestConfiguration;

public abstract class RequestCommonResources {

	protected static final String BASE_URL = "https://api.themoviedb.org/3";
	protected static final String API_KEY = "?api_key=d272326e467344029e68e3c4ff0b4059";
	protected static final String LANGUAGE = "&language=pt-BR";
	protected static final String ADULT = "&adult=true";
	
	protected static ErrorHandler errorHandler = ErrorHandlerBuild.buildErrorHandler();
	
	static {
		Unirest.setObjectMapper(UnirestConfiguration.getObjectMapperConfigurated());
	}
}