package com.ole.rentalstore.httpclient.unirest.tmdb_api;

import org.apache.log4j.Logger;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.ole.rentalstore.commons.dto.tmdb_api.GenreResponseDTO;
import com.ole.rentalstore.httpclient.exceptions.HttpClientException;

public class GenreRequests extends RequestCommonResources {

	protected static final Logger LOGGER = Logger.getLogger(GenreRequests.class);
	private static final String GENRE_REQUESTS_URL = "/genre/movie/list";

	private GenreRequests() {

	}

	public static GenreResponseDTO getGenresList() {
		GenreResponseDTO genreResponse = new GenreResponseDTO();
		HttpResponse<GenreResponseDTO> httpResponse = null;
		try {
			httpResponse = Unirest.get(BASE_URL + GENRE_REQUESTS_URL + API_KEY + LANGUAGE).asObject(GenreResponseDTO.class);
		} catch (UnirestException e) {
			LOGGER.error(e.getMessage());
			throw new HttpClientException();
		}
		if (httpResponse.getStatus() == 200) {
			genreResponse = httpResponse.getBody();
		} else {
			errorHandler.handleError(httpResponse.getStatus());
		}
		return genreResponse;
	}
}