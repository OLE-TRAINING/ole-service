package com.ole.rentalstore.business.service.page_strategy;

import java.util.List;

public abstract class PageStrategy {

	private Integer amountOfMoviesByTmdbResponse = 20;
	private Integer amountOfMoviesByStrategy;
	
	public PageStrategy(Integer amountOfMoviesByStrategy) {
		this.amountOfMoviesByStrategy = amountOfMoviesByStrategy;
	}
	
	public Integer getAppropriatePage(Integer page) {
		return (page - 1) / (amountOfMoviesByTmdbResponse/amountOfMoviesByStrategy) + 1;
	}
	
	// this is an AP, where the constant is equal to the amount of movies per tmdb page (20) divided by the amount of pages on the request.
	public <T> List<T> formatResponseList(List<T> list, Integer page) {
		return PageUtils.getFormattedResponseList(list, amountOfMoviesByStrategy, amountOfMoviesByTmdbResponse/amountOfMoviesByStrategy, page);
	}
    
    public Integer getTotalPages(Integer totalMovies, Integer amount) {
		Integer totalPages = totalMovies / amount;
		if (totalMovies % amount != 0) {
			totalPages += 1;
		}
		return totalPages;
    }
}