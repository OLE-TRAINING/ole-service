package com.ole.rentalstore.business.service.page_strategy;

import java.util.Collections;
import java.util.List;

public class PageUtils {

	public static <T> List<T> getAppropriatedSubList(List<T> list, int from, int to) { 
		try {
			return list.subList(from, to);
		} catch (IndexOutOfBoundsException e) { 
			if (!list.isEmpty()) {
				return list.subList(from, list.size()); // last page
			} else {
				return Collections.emptyList(); // page out of bonds
			}
		}
	}
}