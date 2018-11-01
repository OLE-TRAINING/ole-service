package com.ole.rentalstore.commons.validation;

import java.lang.reflect.Field;

import com.ole.rentalstore.commons.exceptions.bad_request.NullFieldException;

public class NullFieldValidator {

	private NullFieldValidator() {}
	
	public static <T> void checkForNullFields(T entity) throws IllegalAccessException {
		for (Field field : entity.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			if (field.get(entity) == null) {
				throw new NullFieldException(field.getName());
			}
		}
	}
}