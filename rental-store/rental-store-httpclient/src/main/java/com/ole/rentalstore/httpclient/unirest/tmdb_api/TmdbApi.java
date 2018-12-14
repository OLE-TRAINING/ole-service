package com.ole.rentalstore.httpclient.unirest.tmdb_api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ole.rentalstore.httpclient.unirest.configuration.HttpClient;

@Component
public class TmdbApi {

	@Value("${base.url}")
	private String baseUrl;
	
	@Value("${api.key}")
	private String apiKey;
	
	@Value("${language}")
	private String language;
	
	@Value("${include.adult}")
	private String adult;
	
	@Value("${append.to.response}")
	private String appendToResponse;
	
	@Autowired
	protected HttpClient client;
	
	protected String buildUrl(String apiPath) {
		return String.format("%s%s", baseUrl, apiPath);
	}
	
	protected static Map<String, Object> getBaseParams() {
		Map<String, Object> params = new HashMap<>();
		params.put("api_key", "d272326e467344029e68e3c4ff0b4059");
		params.put("language", "pt-BR");
		params.put("include_adult", "true");
		params.put("append_to_response", "");
		return params;
	}
}
