package com.ole.rentalstore.business.service.movie_strategy;

import com.ole.rentalstore.commons.error.ErrorMessage;
import com.ole.rentalstore.commons.error.ErrorResponse;
import com.ole.rentalstore.commons.exceptions.not_found.returning_id.MovieNotFoundByNameException;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.MovieRequests;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.utils.MovieAsTmdbResponseDTO;
import com.ole.rentalstore.httpclient.utils.UrlEncoder;

public class MovieByName implements MovieStrategy {

	@Override
	public MovieAsTmdbResponseDTO getMovies(String id, Integer page) {
		String params = "&query=" + UrlEncoder.getEncodedUrl(id) + MovieUtils.getPageAsParameter(page);
		MovieAsTmdbResponseDTO movieTmdbResponse = MovieRequests.getMoviesListByName(params);
		if (movieTmdbResponse.getTotalMovies().equals(0)) {
			throw new MovieNotFoundByNameException(new ErrorResponse(ErrorMessage.Inexistent.INEXISTENT_MOVIE_NAME), id);
		}
		return movieTmdbResponse;
	}
}