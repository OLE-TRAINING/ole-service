package com.ole.rentalstore.business.service.pagehandler;

import java.util.Collections;
import java.util.List;

public class PageHelper {

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