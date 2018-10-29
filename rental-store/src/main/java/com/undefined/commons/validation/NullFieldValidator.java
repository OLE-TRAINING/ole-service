package com.undefined.commons.validation;

import java.lang.reflect.Field;

import com.undefined.commons.exceptions.NullFieldException;

public class NullFieldValidator {

	private NullFieldValidator() {
		
	}
	
	public static <T> void checkForNullFields(T entity) throws IllegalAccessException {
		for (Field field : entity.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			if (field.get(entity) == null) {
				throw new NullFieldException(field.getName());
			}
		}
	}
}
