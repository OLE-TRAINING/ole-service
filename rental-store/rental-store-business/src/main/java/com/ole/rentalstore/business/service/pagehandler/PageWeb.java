package com.ole.rentalstore.business.service.pagehandler;

import com.ole.rentalstore.httpclient.unirest.tmdb_api.util.MovieAsTmdbResponseDTO;

public class PageWeb extends PageHandler {
	
	@Override
	public MovieAsTmdbResponseDTO handlePage(Integer id, Integer page, Integer amount) {
		if (amount == 20) {
			return makeRequest(id, page);
		} else {
			return this.getNext().handlePage(id, page, amount);
		}
	}
}