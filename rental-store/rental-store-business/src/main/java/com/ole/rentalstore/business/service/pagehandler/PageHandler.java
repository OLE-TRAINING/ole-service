package com.ole.rentalstore.business.service.pagehandler;

import java.util.List;

public interface PageHandler {

	Integer getAppropriatePage(Integer page);
    <T> List<T> formatResponseList(List<T> list, Integer page);
    Integer getTotalPages(Integer currentTotalPages);
}