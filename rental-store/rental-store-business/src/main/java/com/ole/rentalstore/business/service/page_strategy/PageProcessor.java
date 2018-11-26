package com.ole.rentalstore.business.service.page_strategy;

import com.ole.rentalstore.business.service.movie_strategy.MovieStrategy;
import com.ole.rentalstore.business.service.movie_strategy.MovieStrategyFactory;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.util.MovieAsTmdbResponseDTO;

public class PageProcessor {

	private PageProcessor() {
		
	}
	
	public static MovieAsTmdbResponseDTO getMoviesThreatingPagination(String id, Integer amount, Integer page, String filter) {
		PageStrategy pageStrategy = PageStrategyFactory.getPageStrategyByAmount(amount);
		Integer appropriatePage = pageStrategy.getAppropriatePage(page);
		
		MovieStrategy movieStrategy = MovieStrategyFactory.getMovieStrategyByFilter(filter);
		MovieAsTmdbResponseDTO movieTmdbResponse = movieStrategy.getMovies(id, appropriatePage);
		
		movieTmdbResponse.setResults(pageStrategy.formatResponseList(movieTmdbResponse.getResults(), page));
		movieTmdbResponse.setTotalPages(pageStrategy.getTotalPages(movieTmdbResponse.getTotalMovies(), amount));
		
		return movieTmdbResponse;
	}
}