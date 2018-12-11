package com.ole.rentalstore.commons.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class StringManipulatorTest {

	@Test
	public void testGetStringLowerCase() {
		assertThat(StringManipulator.getStringLowerCase("AAAAB")).isEqualTo("aaaab");
	}
}