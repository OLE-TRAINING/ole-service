package com.ole.rentalstore.httpclient.unirest.tmdb_api;

import org.apache.log4j.Logger;

import com.ole.rentalstore.commons.dto.tmdb_api.GenreResponseDTO;

public class GenreRequests extends TmdbCommonResources {

	private static final Logger LOGGER = Logger.getLogger(GenreRequests.class);
	private static final String GENRE_LIST_URL = "/genre/movie/list";

	private GenreRequests() {

	}

	public static GenreResponseDTO getGenresList() {
		String url = BASE_URL + GENRE_LIST_URL + API_KEY + LANGUAGE;
		return makeRequest(GenreResponseDTO.class, url, LOGGER);
	}
}