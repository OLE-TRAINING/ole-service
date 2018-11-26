package com.ole.rentalstore.business.service.page_strategy;

import java.util.List;

public interface PageStrategy {

	Integer getAppropriatePage(Integer page);
    <T> List<T> formatResponseList(List<T> list, Integer page);
    
    default Integer getTotalPages(Integer totalMovies, Integer amount) {
		Integer totalPages = totalMovies / amount;
		if (totalMovies % amount != 0) {
			totalPages += 1;
		}
		return totalPages;
    }
}