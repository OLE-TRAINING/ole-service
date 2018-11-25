package com.ole.rentalstore.business.service.movie_strategy;

import com.ole.rentalstore.httpclient.unirest.tmdb_api.MovieRequests;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.util.MovieAsTmdbResponseDTO;

public class MovieByGenre implements MovieStrategy {

	@Override
	public <T> MovieAsTmdbResponseDTO getMovies(T id, Integer page) {
		StringBuilder params = new StringBuilder();
		params.append(id.equals(-1)? "&primary_release_year=2018&sort_by=popularity.desc" : "&with_genres=" + id);
		params.append(MovieUtils.getPageAsParameter(page));
		return MovieRequests.getMoviesListByGenre(params.toString());
	}
}