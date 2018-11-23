package com.ole.rentalstore.business.service.movie_strategy;

public class MovieUtils {

	private MovieUtils() {
		
	}
	
	public static String getPageAsParameter(Integer page) {
		return "&page=" + page;
	}
}