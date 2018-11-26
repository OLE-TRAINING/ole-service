package com.ole.rentalstore.httpclient.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import com.ole.rentalstore.httpclient.exceptions.HttpClientException;

public class UrlEncoder {
	
	private static final Logger LOGGER = Logger.getLogger(UrlEncoder.class);

	private UrlEncoder() {
		
	}
	
	public static String getEncodedUrl(String url) {
		try {
			return URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage());
			throw new HttpClientException(e.getMessage());
		}
	}
}