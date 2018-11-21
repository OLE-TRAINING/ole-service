package com.ole.rentalstore.business.service.pagehandler;

import com.ole.rentalstore.httpclient.unirest.tmdb_api.GenreRequests;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.util.MovieAsTmdbResponseDTO;

public abstract class PageHandler {

	private PageHandler next;

	public PageHandler getNext() {
		return next;
	}

	public void setNext(PageHandler next) {
		this.next = next;
	}
	
	public abstract MovieAsTmdbResponseDTO handlePage(Integer id, Integer page, Integer amount);
			
	protected MovieAsTmdbResponseDTO makeRequest(Integer id, Integer page) {
		return GenreRequests.getMoviesListByGenre(id, page);
	}
}