package com.ole.rentalstore.httpclient.unirest.tmdb_api;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONException;
import org.junit.Test;

public class GenreRequestsTest {

	@Test
	public void testGetGenresList() throws JSONException {
		assertThat(GenreRequests.getGenresList().getGenres()).isNotNull();
	}
}