package com.ole.rentalstore.business.service.movie_strategy;

public enum MovieStrategyFactory {

	BY_GENRES("genres", new MovieByGenre()),
	BY_NAME("name", new MovieByName()),
	BY_SIMILARITY("similarity", new MovieBySimilarity());
	
	private String filter;
	private MovieStrategy movieStrategy;
	
	MovieStrategyFactory(String filter, MovieStrategy movieStrategy) {
		this.filter = filter;
		this.movieStrategy = movieStrategy;
	}

	public String getFilter() {
		return filter;
	}
	
	public MovieStrategy getMovieStrategy() {
		return movieStrategy;
	}
	
	public static MovieStrategy getMovieStrategyByFilter(String filter) {
		for (MovieStrategyFactory movieStrategyFactory : MovieStrategyFactory.values()) {
			if (movieStrategyFactory.getFilter().equals(filter)) {
				return movieStrategyFactory.getMovieStrategy();
			}
		}
		return null;
	}
}
