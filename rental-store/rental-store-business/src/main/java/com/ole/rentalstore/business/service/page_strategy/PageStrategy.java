package com.ole.rentalstore.business.service.page_strategy;

import java.util.List;

public interface PageStrategy {

	Integer getAppropriatePage(Integer page);
    <T> List<T> formatResponseList(List<T> list, Integer page);
    Integer getTotalPages(Integer totalMovies, Integer amount);
}