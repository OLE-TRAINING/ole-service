package com.ole.rentalstore.business.service;

import org.springframework.stereotype.Service;

import com.ole.rentalstore.commons.dto.tmdb_api.GenreDTO;
import com.ole.rentalstore.commons.dto.tmdb_api.GenreResponseDTO;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.GenreRequests;

@Service
public class GenreService {

	public GenreResponseDTO getMovieGenres() {
		GenreResponseDTO genres = GenreRequests.getGenresList();
		genres.addGenre(new GenreDTO(-1, "Últimos filmes"));
		return genres;
	}
}