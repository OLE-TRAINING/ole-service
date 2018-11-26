package com.ole.rentalstore.business.service.page_strategy;

import java.util.List;

public class PageWebSimilarMovies implements PageStrategy {

	@Override
	public Integer getAppropriatePage(Integer page) {
		return null;
	}

	@Override
	public <T> List<T> formatResponseList(List<T> list, Integer page) {
		return null;
	}
}