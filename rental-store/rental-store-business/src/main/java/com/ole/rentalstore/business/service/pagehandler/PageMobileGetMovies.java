package com.ole.rentalstore.business.service.pagehandler;

import java.util.List;

public class PageMobileGetMovies implements PageHandler {

	@Override
	public Integer getAppropriatePage(Integer page) {
		if (page % 2 == 0) {
			return page/2;
		} else {
			return page/2 + 1;
		}
	}

	@Override
	public <T> List<T> formatResponseList(List<T> list, Integer page) {
		if (page % 2 == 0) {
			return PageHelper.getAppropriatedSubList(list, 10, 20);
		} else {
			return PageHelper.getAppropriatedSubList(list, 0, 10);
		}
	}

	@Override
	public Integer getTotalPages(Integer currentTotalPages) {
		return currentTotalPages * 2;
	}
}