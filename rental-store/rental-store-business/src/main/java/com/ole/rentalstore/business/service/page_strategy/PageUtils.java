package com.ole.rentalstore.business.service.page_strategy;

import java.util.Collections;
import java.util.List;

public class PageUtils {
	
	private PageUtils() {
		
	}

	private static <T> List<T> getAppropriatedSubList(List<T> list, int from, int to) { 
		try {
			return list.subList(from, to);
		} catch (IndexOutOfBoundsException e) { 
			if (!list.isEmpty() && from < list.size()) {
				return list.subList(from, list.size()); // last page
			} else {
				return Collections.emptyList(); // page out of bonds
			}
		}
	}
	
	private static boolean belongsToAP(Integer apConstant, Integer firstValue, Integer currentValue) {
		if (currentValue.equals(firstValue)) {
			return true;
		} 
		double n = ((currentValue - firstValue)/(apConstant * 1.0));
		return n == (int)n && n > 0;
	}
	
	public static <T> List<T> getFormattedResponseList(List<T> list, Integer amountOfMoviesPerPage, Integer apConstant, Integer page) {
		for (int i = 1; i < amountOfMoviesPerPage; i++) {
			if (belongsToAP(apConstant, i, page)) {
				return getAppropriatedSubList(list, amountOfMoviesPerPage * (i - 1), amountOfMoviesPerPage * i);
			}
		}
		return Collections.emptyList();
	}
}