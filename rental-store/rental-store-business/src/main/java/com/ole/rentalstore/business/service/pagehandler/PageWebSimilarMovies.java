package com.ole.rentalstore.business.service.pagehandler;

import java.util.List;

public class PageWebSimilarMovies implements PageHandler {

	@Override
	public Integer getAppropriatePage(Integer page) {
		return null;
	}

	@Override
	public <T> List<T> formatResponseList(List<T> list, Integer page) {
		return null;
	}

	@Override
	public Integer getTotalPages(Integer currentTotalPages) {
		return null;
	}
}