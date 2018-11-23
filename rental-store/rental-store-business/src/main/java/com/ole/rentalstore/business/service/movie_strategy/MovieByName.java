package com.ole.rentalstore.business.service.movie_strategy;

import com.ole.rentalstore.httpclient.unirest.tmdb_api.MovieRequests;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.util.MovieAsTmdbResponseDTO;

public class MovieByName implements MovieStrategy {

	@Override
	public <T> MovieAsTmdbResponseDTO getMovies(T id, Integer page) {
		String params = "&query=" + id + MovieUtils.getPageAsParameter(page);
		return MovieRequests.getMoviesListByGenre(params);
	}
}