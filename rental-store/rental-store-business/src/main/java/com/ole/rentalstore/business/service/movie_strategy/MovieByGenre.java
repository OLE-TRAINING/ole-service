package com.ole.rentalstore.business.service.movie_strategy;

import com.ole.rentalstore.httpclient.unirest.tmdb_api.MovieRequests;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.utils.MovieAsTmdbResponseDTO;

public class MovieByGenre implements MovieStrategy {
	
	private String getAppropriateGenre(String id) {
		return id.equals("-1")? "&primary_release_year=2018&sort_by=popularity.desc" : "&with_genres=" + id;
	}

	@Override
	public MovieAsTmdbResponseDTO getMovies(String id, Integer page) {
		StringBuilder params = new StringBuilder();
		params.append(getAppropriateGenre(id));
		params.append(MovieUtils.getPageAsParameter(page));
		return MovieRequests.getMoviesListByGenre(params.toString());
	}
}