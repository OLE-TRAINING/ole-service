package com.ole.rentalstore.httpclient.unirest.configuration;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.mashape.unirest.http.HttpMethod;
import com.mashape.unirest.request.HttpRequest;
import com.ole.rentalstore.httpclient.unirest.UnirestCommonResources;

@Component
public class HttpClient extends UnirestCommonResources {
		
	public <T> T get(String url, Map<String, Object> params, Map<String, Object> headers, Class<T> resultClazz) {
		HttpRequest request = buildRequest(HttpMethod.GET, url, params, headers);
		return handleRequest(request, resultClazz);
	}
	
	public <T> T get(String url, Map<String, Object> params, Class<T> resultClazz) {
		return get(url, params, null, resultClazz);
	}
	
	public <T> T post(String url, Map<String, Object> params, Map<String, Object> headers, Class<T> resultClazz) {
		HttpRequest request = buildRequest(HttpMethod.POST, url, params, headers);
		return handleRequest(request, resultClazz);
	}	
}