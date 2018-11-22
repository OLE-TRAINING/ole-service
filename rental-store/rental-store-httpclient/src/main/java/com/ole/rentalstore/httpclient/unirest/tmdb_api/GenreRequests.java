

package com.ole.rentalstore.httpclient.unirest.tmdb_api;

import org.apache.log4j.Logger;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.ole.rentalstore.commons.dto.tmdb_api.GenreResponseDTO;
import com.ole.rentalstore.httpclient.exceptions.HttpClientException;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.util.MovieAsTmdbResponseDTO;

public class GenreRequests extends RequestCommonResources {

	protected static final Logger LOGGER = Logger.getLogger(GenreRequests.class);
	private static final String GENRE_LIST_URL = "/genre/movie/list";
	private static final String MOVIE_LIST_BY_GENRE_URL = "/discover/movie";

	private GenreRequests() {

	}

	public static GenreResponseDTO getGenresList() {
		GenreResponseDTO genreResponse = new GenreResponseDTO();
		HttpResponse<GenreResponseDTO> httpResponse = null;
		try {
			httpResponse = Unirest.get(BASE_URL + GENRE_LIST_URL + API_KEY + LANGUAGE).asObject(GenreResponseDTO.class);
		} catch (UnirestException e) {
			LOGGER.error(e.getMessage());
			throw new HttpClientException(e.getMessage());
		}
		if (httpResponse.getStatus() == 200) {
			genreResponse = httpResponse.getBody();
		} else {
			errorHandler.handleError(httpResponse.getStatus());
		}
		return genreResponse;
	}
	
	public static MovieAsTmdbResponseDTO getMoviesListByGenre(Integer id, Integer page) {
		MovieAsTmdbResponseDTO movieTmdbRespons = null;
		HttpResponse<MovieAsTmdbResponseDTO> httpResponse = null;
		// if the genre is latest releases, set year to current year and sort by popularity
		String genre = id == -1? "&primary_release_year=2018&sort_by=popularity.desc" : "&with_genres=" + id;
		try {
			httpResponse = Unirest.get(BASE_URL + MOVIE_LIST_BY_GENRE_URL + API_KEY + LANGUAGE + ADULT + genre + "&page=" + page).asObject(MovieAsTmdbResponseDTO.class);
		} catch(UnirestException e) {
			LOGGER.error(e.getMessage());
			throw new HttpClientException(e.getMessage());
		}
		if (httpResponse.getStatus() == 200) {
			movieTmdbRespons = httpResponse.getBody();
		} else {
			errorHandler.handleError(httpResponse.getStatus());
		}
		return movieTmdbRespons;
	}
}