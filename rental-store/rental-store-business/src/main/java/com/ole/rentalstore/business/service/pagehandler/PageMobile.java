package com.ole.rentalstore.business.service.pagehandler;

import java.util.Collections;
import java.util.List;

import com.ole.rentalstore.httpclient.unirest.tmdb_api.util.MovieAsTmdbResponseDTO;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.util.MovieExtractedFromMoviesByGenreServiceDTO;

public class PageMobile extends PageHandler {
	
	// since TMDB returns 20 movies per page and mobile only requests 10, this method controls which half is returned
	private List<MovieExtractedFromMoviesByGenreServiceDTO> getAppropriatedHalf(List<MovieExtractedFromMoviesByGenreServiceDTO> movies, int from, int to) { 
		try {
			return movies.subList(from, to);
		} catch (IndexOutOfBoundsException e) { 
			if (!movies.isEmpty()) {
				return movies.subList(from, movies.size()); // last page
			} else {
				return Collections.emptyList(); // page out of bonds
			}
		}
	}
	
	@Override
	public MovieAsTmdbResponseDTO handlePage(Integer id, Integer page, Integer amount) {
		if (amount == 10) {
			MovieAsTmdbResponseDTO movieTmdbResponse = null;
			List<MovieExtractedFromMoviesByGenreServiceDTO> moviesList = null;
			if (page % 2 == 0) {
				movieTmdbResponse = makeRequest(id, page/2);
				moviesList = getAppropriatedHalf(movieTmdbResponse.getResults(), 10, 20);
			} else {
				movieTmdbResponse = makeRequest(id, page/2 + 1);
				moviesList = getAppropriatedHalf(movieTmdbResponse.getResults(), 0, 10);
			}
			movieTmdbResponse.setTotalPages(movieTmdbResponse.getTotalPages()*2);
			movieTmdbResponse.setResults(moviesList);
			return movieTmdbResponse;
		} else {
			return this.getNext().handlePage(id, page, amount);
		}
	}
}