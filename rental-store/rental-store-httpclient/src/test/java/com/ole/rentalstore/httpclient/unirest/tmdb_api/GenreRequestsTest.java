package com.ole.rentalstore.httpclient.unirest.tmdb_api;

import org.json.JSONException;
import org.junit.Test;
import org.junit.Test.None;

import com.ole.rentalstore.httpclient.unirest.tmdb_api.util.MovieAsTmdbResponseDTO;

public class GenreRequestsTest {

	@Test(expected = None.class)
	public void testGetGenresList() throws JSONException {
		GenreRequests.getGenresList();
	}
	
	@Test
	public void testGetMoviesListByGenre() {
		MovieAsTmdbResponseDTO movies = GenreRequests.getMoviesListByGenre(12);
		System.out.println(movies.getResults());
	}
}