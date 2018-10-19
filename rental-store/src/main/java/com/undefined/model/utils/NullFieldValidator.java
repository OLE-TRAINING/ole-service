package com.undefined.model.utils;

import java.lang.reflect.Field;

import com.undefined.model.exceptions.NullFieldException;

public class NullFieldValidator {

	private NullFieldValidator() {
		
	}
	
	public static <T> void checkForNullFields(T entity) throws IllegalAccessException {
		for (Field field : entity.getClass().getFields()) {
			if (field.get(entity) == null) {
				throw new NullFieldException(field.getName());
			}
		}
	}
}
