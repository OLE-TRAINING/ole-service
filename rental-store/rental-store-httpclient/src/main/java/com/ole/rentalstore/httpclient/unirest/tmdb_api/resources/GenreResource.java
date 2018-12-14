package com.ole.rentalstore.httpclient.unirest.tmdb_api.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ole.rentalstore.commons.dto.tmdb_api.GenreResponseDTO;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.TmdbApi;

@Component
public class GenreResource extends TmdbApi {

	@Value("${genre.list.url}")
	private String genreListUrl;
	
	public GenreResponseDTO getGenresList() {
		String url = buildUrl(genreListUrl);
		return client.get(url, getBaseParams(), GenreResponseDTO.class);
	}
}
