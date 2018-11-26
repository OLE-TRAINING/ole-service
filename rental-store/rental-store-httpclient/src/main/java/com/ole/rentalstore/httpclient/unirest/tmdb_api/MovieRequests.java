package com.ole.rentalstore.httpclient.unirest.tmdb_api;

import org.apache.log4j.Logger;

import com.ole.rentalstore.httpclient.unirest.tmdb_api.util.MovieAsTmdbResponseDTO;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.util.MovieDetailedAsTmdbResponseDTO;

public class MovieRequests extends TmdbCommonResources {

	private static final Logger LOGGER = Logger.getLogger(GenreRequests.class);
	private static final String MOVIE_LIST_BY_GENRE_URL = "/discover/movie";
	private static final String MOVIE_LIST_BY_SIMILARITY_URL = "/movie/%s/similar";
	private static final String MOVIE_LIST_BY_NAME_URL = "/search/movie";
	private static final String MOVIE_DETAILS_URL = "/movie/%s";
	
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
	
	public static MovieAsTmdbResponseDTO getMoviesListBySimilarity(String movieId, String params) {
		String url = BASE_URL + String.format(MOVIE_LIST_BY_SIMILARITY_URL, movieId) + API_KEY + LANGUAGE  + params;
		return makeRequest(MovieAsTmdbResponseDTO.class, url, LOGGER);
	}
	
	public static MovieDetailedAsTmdbResponseDTO getMovieDetails(Integer movieId) {
		String url = BASE_URL + String.format(MOVIE_DETAILS_URL, movieId) + API_KEY + LANGUAGE;
		return makeRequest(MovieDetailedAsTmdbResponseDTO.class, url, LOGGER);
	}
}