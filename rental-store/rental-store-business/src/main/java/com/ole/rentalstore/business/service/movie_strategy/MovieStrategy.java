package com.ole.rentalstore.business.service.movie_strategy;

import com.ole.rentalstore.httpclient.unirest.tmdb_api.util.MovieAsTmdbResponseDTO;

public interface MovieStrategy {

	MovieAsTmdbResponseDTO getMovies(String id, Integer page);
}