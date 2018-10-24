package com.undefined.model.validation;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.BeforeClass;
import org.junit.Test;

import com.undefined.commons.validation.UserValidator;

public class PasswordValidationTest {

	private static Method method;
	
	@BeforeClass
	public static void initialize() throws NoSuchMethodException, SecurityException {
		method = UserValidator.class.getDeclaredMethod("validatePassword", String.class);
		method.setAccessible(true);
	}
	
	@Test
	public void testValidatePasswordInvalidSpecialChar() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean isValid = (boolean) method.invoke(null, "nomequal98*quer");
		assertThat(isValid).isEqualTo(false);
	}
	
	@Test
	public void testValidatePasswordInvalidSpace() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean isValid = (boolean) method.invoke(null, "nomeq ual98quer");
		assertThat(isValid).isEqualTo(false);
	}
	
	@Test
	public void testValidatePasswordInvalidMinimumLength() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean isValid = (boolean) method.invoke(null, "nom");
		assertThat(isValid).isEqualTo(false);
	}
	
	@Test
	public void testValidatePasswordInvalidMaximumLength() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean isValid = (boolean) method.invoke(null, "passwordpasswordpassword");
		assertThat(isValid).isEqualTo(false);
	}
	
	@Test
	public void testValidatePasswordInvalidOnlyLetters() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean isValid = (boolean) method.invoke(null, "password");
		assertThat(isValid).isEqualTo(false);
	}
	
	@Test
	public void testValidatePasswordInvalidOnlyNumbers() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean isValid = (boolean) method.invoke(null, "1234567");
		assertThat(isValid).isEqualTo(false);
	}
	
	@Test
	public void testValidatePasswordValid() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean isValid = (boolean) method.invoke(null, "pass123");
		assertThat(isValid).isEqualTo(true);
	}
}