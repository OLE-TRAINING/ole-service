package com.ole.rentalstore.httpclient.unirest.tmdb_api;

import org.json.JSONException;
import org.junit.Test;
import org.junit.Test.None;

import com.mashape.unirest.http.exceptions.UnirestException;

public class GenreRequestsTest {

	@Test(expected = None.class)
	public void testGetGenresList() throws JSONException, UnirestException {
		GenreRequests.getGenresList();
	}
}