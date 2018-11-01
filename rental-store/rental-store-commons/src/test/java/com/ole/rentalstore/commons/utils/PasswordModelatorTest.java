package com.ole.rentalstore.commons.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class PasswordModelatorTest {

	@Test
	public void testGetEncryptedPasswordTestOne() {
		assertThat(PasswordModelator.getEncryptedPassword("pvbcvc1")).isEqualTo("821e2091e5502290cdfdb499a3838d19");
	}
	
	@Test
	public void testGetEncryptedPasswordTestTwo() {
		assertThat(PasswordModelator.getEncryptedPassword("pass123")).isEqualTo("32250170a0dca92d53ec9624f336ca24");
	}
	@Test
	public void testGetEncryptedPasswordTestThree() {
		assertThat(PasswordModelator.getEncryptedPassword("dojkd767")).isEqualTo("6d84e026ed87c40f105c9431494c5f0b");
	}
	
	@Test
	public void testGetEncryptedPasswordTestFour() {
		assertThat(PasswordModelator.getEncryptedPassword("hhhh543")).isEqualTo("ac875c2f33c0577532cf70f075b64d55");
	}
	
	@Test
	public void testGetEncryptedPasswordTestFive() {
		assertThat(PasswordModelator.getEncryptedPassword("jhjhjh88")).isEqualTo("acdae2bf4decc4e8db72f599e9057a7b");
	}
}