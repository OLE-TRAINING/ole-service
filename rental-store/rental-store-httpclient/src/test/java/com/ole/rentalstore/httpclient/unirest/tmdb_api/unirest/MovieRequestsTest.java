package com.ole.rentalstore.httpclient.unirest.tmdb_api.unirest;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.MovieRequests;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.utils.MovieAsTmdbResponseDTO;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.utils.MovieDetailedAsTmdbResponseDTO;

public class MovieRequestsTest extends BaseRequestsTest {
	
	@BeforeClass
	public static void setUp() {
		BaseRequestsTest.setUp();
	}
	
	@Test
	public void testGetMoviesListByName() throws JsonParseException, JsonMappingException, IOException {
		String params = "&query=Venom&page=1";
		assertThat(MovieRequests.getMoviesListByName(params)).isEqualTo(new ObjectMapper().readValue(getMoviesByNameJson, MovieAsTmdbResponseDTO.class));
	}
	
	@Test
	public void testGetMoviesListByGenre() throws JsonParseException, JsonMappingException, IOException {
		String params = "&with_genres=12&page=1";
		assertThat(MovieRequests.getMoviesListByGenre(params)).isEqualTo(new ObjectMapper().readValue(getMoviesByGenreJson, MovieAsTmdbResponseDTO.class));
	}
	
	@Test
	public void testGetMoviesListBySimilarity() throws JsonParseException, JsonMappingException, IOException {
		String params = "&page=1";
		assertThat(MovieRequests.getMoviesListBySimilarity("123", params)).isEqualTo(new ObjectMapper().readValue(getMoviesBySimilarityJson, MovieAsTmdbResponseDTO.class));
	}
	
	@Test
	public void testGetMovieDetails() throws JsonParseException, JsonMappingException, IOException {
		assertThat(MovieRequests.getMovieDetails(123)).isEqualTo(new ObjectMapper().readValue(getMovieDetailsJson, MovieDetailedAsTmdbResponseDTO.class));
	}
}