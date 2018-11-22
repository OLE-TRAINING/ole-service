package com.ole.rentalstore.business.service.pagehandler;

import java.util.List;

public class PageWebGetMovies implements PageHandler {

	@Override
	public Integer getAppropriatePage(Integer page) {
		return page;
	}

	@Override
	public <T> List<T> formatResponseList(List<T> list, Integer page) {
		return list;
	}

	@Override
	public Integer getTotalPages(Integer currentTotalPages) {
		return currentTotalPages;
	}
}