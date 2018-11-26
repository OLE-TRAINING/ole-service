package com.ole.rentalstore.business.service.page_strategy;

import java.util.List;

public class PageMobileGetMovies implements PageStrategy {

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
			return PageUtils.getAppropriatedSubList(list, 10, 20);
		} else {
			return PageUtils.getAppropriatedSubList(list, 0, 10);
		}
	}
}