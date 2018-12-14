package com.ole.rentalstore.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ole.rentalstore.commons.dto.tmdb_api.GenreDTO;
import com.ole.rentalstore.commons.dto.tmdb_api.GenreResponseDTO;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.GenreRequests;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.resources.GenreResource;

@Service
public class GenreService {
	
	@Autowired
	private GenreResource resource;

	public GenreResponseDTO getMovieGenres() {
		GenreResponseDTO genres = GenreRequests.getGenresList();
		genres.addGenre(new GenreDTO(-1, "Últimos filmes"));
		return genres;
	}
	
	public GenreResponseDTO getMovieGenresV2() {
		GenreResponseDTO genres = resource.getGenresList();
		genres.addGenre(new GenreDTO(-1, "Últimos filmes"));
		return genres;
	}
}