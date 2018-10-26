package com.undefined.commons.validation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class TokenValidationTest {

	@Test
	public void testValidateTokenValidOnlyLetters() {
		assertThat(TokenValidator.validateToken("llllll")).isEqualTo(true);
	}
	
	@Test
	public void testValidateTokenValidNumbersAndLetters() {
		assertThat(TokenValidator.validateToken("lll2l5")).isEqualTo(true);
	}
	
	@Test
	public void testValidateTokenValidOnlyNumbers() {
		assertThat(TokenValidator.validateToken("123456")).isEqualTo(true);
	}
	
	@Test
	public void testValidateTokenInvalidLessThanSixDigits() {
		assertThat(TokenValidator.validateToken("lllll")).isEqualTo(false);
	}
	
	@Test
	public void testValidateTokenInvalidMoreThanSixDigits() {
		assertThat(TokenValidator.validateToken("lllllll")).isEqualTo(false);
	}
	
	@Test
	public void testValidateTokenInvalidSpecialCharcaters1() {
		assertThat(TokenValidator.validateToken("12345*")).isEqualTo(false);
	}
	
	@Test
	public void testValidateTokenInvalidSpecialCharcaters2() {
		assertThat(TokenValidator.validateToken("12345)")).isEqualTo(false);
	}
	
	@Test
	public void testValidateTokenInvalidSpecialCharcaters3() {
		assertThat(TokenValidator.validateToken("12345_")).isEqualTo(false);
	}
	
	@Test
	public void testValidateTokenInvalidSpecialCharcaters4() {
		assertThat(TokenValidator.validateToken("12345 ")).isEqualTo(false);
	}
}