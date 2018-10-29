package com.undefined.commons.validation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class EmailValidatorTest {
	
	@Test
	public void testValidEmail() {
		boolean isValid = UserValidator.validateEmail("teste@teste.com");
		assertThat(isValid).isEqualTo(true);
	}
	
	@Test
	public void testInvalidEmailDoubleAt() {
		boolean isValid = UserValidator.validateEmail("teste@@teste.com");
		assertThat(isValid).isEqualTo(false);
	}
	
	@Test
	public void testInvalidEmailDotBeforeAt() {
		boolean isValid = UserValidator.validateEmail("teste.@teste.com");
		assertThat(isValid).isEqualTo(false);
	}
	
	@Test
	public void testInvalidEmailStartWithDot() {
		boolean isValid = UserValidator.validateEmail(".teste.@teste.com");
		assertThat(isValid).isEqualTo(false);
	}
	
	@Test
	public void testInvalidEmailDotAfterAt() {
		boolean isValid = UserValidator.validateEmail("teste@.teste.com");
		assertThat(isValid).isEqualTo(false);
	}
}