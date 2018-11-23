package com.ole.rentalstore.business.service.movie_strategy;

import com.ole.rentalstore.httpclient.unirest.tmdb_api.util.MovieAsTmdbResponseDTO;

public interface MovieStrategy {

	<T> MovieAsTmdbResponseDTO getMovies(T id, Integer page);
}