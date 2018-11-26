package com.ole.rentalstore.business.service.movie_strategy;

import com.ole.rentalstore.httpclient.unirest.tmdb_api.MovieRequests;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.util.MovieAsTmdbResponseDTO;
import com.ole.rentalstore.httpclient.utils.UrlEncoder;

public class MovieByName implements MovieStrategy {

	@Override
	public MovieAsTmdbResponseDTO getMovies(String id, Integer page) {
		String params = "&query=" + UrlEncoder.getEncodedUrl(id) + MovieUtils.getPageAsParameter(page);
		return MovieRequests.getMoviesListByName(params);
	}
}