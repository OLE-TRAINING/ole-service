package com.ole.rentalstore.httpclient.unirest.tmdb_api.unirest;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.ole.rentalstore.httpclient.unirest.configuration.UnirestConfiguration;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.GenreRequests;

public class GenreRequestsTest extends BaseRequestsTest {
	
	@Rule
	public WireMockClassRule instanceRule = wireMockRule;
	
	@BeforeClass
	public static void setUp() {
		Unirest.setObjectMapper(UnirestConfiguration.getObjectMapperConfigurated());
		BaseRequestsTest.setUp();
	}
	
	@Test
	public void exampleTest1() throws UnirestException {
	    System.out.println(GenreRequests.getGenresList().getGenres() + "\n\n\n\n\n");
	}
}