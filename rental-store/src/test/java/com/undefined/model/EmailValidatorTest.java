package com.undefined.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.undefined.model.utils.EmailValidator;

public class EmailValidatorTest {
	
	@Test
	public void testValidEmail() {
		boolean isValid = EmailValidator.validateEmail("teste@teste.com");
		assertThat(isValid).isEqualTo(true);
	}
	
	@Test
	public void testInvalidEmailDoubleAt() {
		boolean isValid = EmailValidator.validateEmail("teste@@teste.com");
		assertThat(isValid).isEqualTo(false);
	}
	
	@Test
	public void testInvalidEmailDotBeforeAt() {
		boolean isValid = EmailValidator.validateEmail("teste.@teste.com");
		assertThat(isValid).isEqualTo(false);
	}
	
	@Test
	public void testInvalidEmailStartWithDot() {
		boolean isValid = EmailValidator.validateEmail(".teste.@teste.com");
		assertThat(isValid).isEqualTo(false);
	}
	
	@Test
	public void testInvalidEmailDotAfterAt() {
		boolean isValid = EmailValidator.validateEmail("teste@.teste.com");
		assertThat(isValid).isEqualTo(false);
	}
}