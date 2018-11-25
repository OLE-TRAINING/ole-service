package com.ole.rentalstore.httpclient.unirest;

import com.mashape.unirest.http.Unirest;
import com.ole.rentalstore.httpclient.unirest.configuration.UnirestConfiguration;

public class UnirestCommonResources {
	
	static {
		Unirest.setObjectMapper(UnirestConfiguration.getObjectMapperConfigurated());
	}
}