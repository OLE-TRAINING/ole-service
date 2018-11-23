package com.ole.rentalstore.httpclient.unirest.tmdb_api;

import org.apache.log4j.Logger;

import com.ole.rentalstore.httpclient.unirest.tmdb_api.util.MovieAsTmdbResponseDTO;

public class MovieRequests extends TmdbCommonResources {

	private static final Logger LOGGER = Logger.getLogger(GenreRequests.class);
	private static final String MOVIE_LIST_BY_GENRE_URL = "/discover/movie";
	private static final String MOVIE_LIST_BY_SIMILARITY_URL = "/discover/movie";
	private static final String MOVIE_LIST_BY_NAME_URL = "/discover/movie";
	
	private MovieRequests() {

	}

	public static MovieAsTmdbResponseDTO getMoviesListByGenre(String params) {
		String url = BASE_URL + MOVIE_LIST_BY_GENRE_URL + API_KEY + LANGUAGE + ADULT + params;
		return makeRequest(MovieAsTmdbResponseDTO.class, url, LOGGER);
	}
	
	public static MovieAsTmdbResponseDTO getMoviesListByName(String params) {
		String url = BASE_URL + MOVIE_LIST_BY_NAME_URL + API_KEY + LANGUAGE + ADULT + params;
		return makeRequest(MovieAsTmdbResponseDTO.class, url, LOGGER);
	}
	
	public static MovieAsTmdbResponseDTO getMoviesListBySimilarity(String params) {
		String url = BASE_URL + MOVIE_LIST_BY_SIMILARITY_URL + API_KEY + LANGUAGE + ADULT + params;
		return makeRequest(MovieAsTmdbResponseDTO.class, url, LOGGER);
	}
}