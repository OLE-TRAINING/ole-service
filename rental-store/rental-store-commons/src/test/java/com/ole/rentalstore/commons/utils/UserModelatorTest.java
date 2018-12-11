package com.ole.rentalstore.commons.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class UserModelatorTest {

	@Test
	public void testGetInexistingUser() {
		assertThat(UserModelator.getInexistingUser().getRegistrationStatus()).isEqualTo(RegistrationStatus.INEXISTENT);
	}
}