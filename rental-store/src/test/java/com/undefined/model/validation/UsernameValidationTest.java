package com.undefined.model.validation;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.BeforeClass;
import org.junit.Test;

import com.undefined.commons.validation.UserValidator;

public class UsernameValidationTest {

	private static Method method;
	
	@BeforeClass
	public static void initialize() throws NoSuchMethodException, SecurityException {
		method = UserValidator.class.getDeclaredMethod("validateUsername", String.class);
		method.setAccessible(true);
	}
	
	@Test
	public void testValidateUsernameInvalidSpecialChar() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean isValid = (boolean) method.invoke(null, "nomequal98*quer");
		assertThat(isValid).isEqualTo(false);
	}
	
	@Test
	public void testValidateUsernameInvalidSpace() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean isValid = (boolean) method.invoke(null, "nome qual98*quer");
		assertThat(isValid).isEqualTo(false);
	}
	
	@Test
	public void testValidateUsernameInvalidEmpty() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean isValid = (boolean) method.invoke(null, "");
		assertThat(isValid).isEqualTo(false);
	}
	
	@Test
	public void testValidateUsernameInvalidLengthExceeded() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean isValid = (boolean) method.invoke(null, "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
		assertThat(isValid).isEqualTo(false);
	}
	
	@Test
	public void testValidateUsernameValid() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean isValid = (boolean) method.invoke(null, "nomequal98quer");
		assertThat(isValid).isEqualTo(true);
	}
}