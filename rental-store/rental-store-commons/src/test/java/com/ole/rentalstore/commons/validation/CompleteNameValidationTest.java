package com.ole.rentalstore.commons.validation;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.BeforeClass;
import org.junit.Test;

public class CompleteNameValidationTest {

	private static Method method;
	
	@BeforeClass
	public static void initialize() throws NoSuchMethodException, SecurityException {
		method = UserValidator.class.getDeclaredMethod("validateName", String.class);
		method.setAccessible(true);
	}
	
	@Test
	public void testValidateNameInvalidNumber() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean isValid = (boolean) method.invoke(null, "nomequal98quer");
		assertThat(isValid).isEqualTo(false);
	}
	
	@Test
	public void testValidateNameInvalidSpecialChar() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean isValid = (boolean) method.invoke(null, "nomequal_quer");
		assertThat(isValid).isEqualTo(false);
	}
	
	@Test
	public void testValidateNameInvalidDoubleSpace() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean isValid = (boolean) method.invoke(null, "nomequal  quer");
		assertThat(isValid).isEqualTo(false);
	}
	
	@Test
	public void testValidateNameInvalidBeginningSpace() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean isValid = (boolean) method.invoke(null, " nomequalquer");
		assertThat(isValid).isEqualTo(false);
	}
	
	@Test
	public void testValidateNameInvalidEmpty() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean isValid = (boolean) method.invoke(null, "");
		assertThat(isValid).isEqualTo(false);
	}
	
	@Test
	public void testValidateUsernameInvalidLengthExceeded() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean isValid = (boolean) method.invoke(null, "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
		assertThat(isValid).isEqualTo(false);
	}
	
	@Test
	public void testValidateNameValidSpace() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean isValid = (boolean) method.invoke(null, "nomequal quer");
		assertThat(isValid).isEqualTo(true);
	}
	
	@Test
	public void testValidateNameValid() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean isValid = (boolean) method.invoke(null, "nomequalquer");
		assertThat(isValid).isEqualTo(true);
	}
}