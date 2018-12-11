package com.ole.rentalstore.commons.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

public class RandomGeneratorTest {

	@Test
	public void testGetRandomBoolean() {
		assertThat(RandomGenerator.getRandomBoolean()).isInstanceOf(Boolean.class);
	}
	
	@Test
	public void testGetRandomPrice() {
		assertThat(RandomGenerator.getRandomPrice()).isInstanceOf(BigDecimal.class)
			.isLessThan(new BigDecimal("70"))
			.isGreaterThanOrEqualTo(new BigDecimal("1.99"));
	}
	
	@Test
	public void testGetFormattedPrice() {
		assertThat(RandomGenerator.getFormattedPrice(new BigDecimal("50.10101010"))).isEqualTo(new BigDecimal("50.10"));
	}
	
	@Test
	public void testGetRandomRuntime() {
		assertThat(RandomGenerator.getRandomRuntime()).isInstanceOf(Integer.class)
			.isLessThan(181)
			.isGreaterThanOrEqualTo(50);
	}
	
	@Test
	public void testGetFormattedRuntimeLessThanHour() {
		assertThat(RandomGenerator.getFormattedRuntime(20)).isEqualTo("20min");
	}
	
	@Test
	public void testGetFormattedRuntimeExactHour() {
		assertThat(RandomGenerator.getFormattedRuntime(60)).isEqualTo("1h");
	}
	
	@Test
	public void testGetFormattedRuntimeOverOneHour() {
		assertThat(RandomGenerator.getFormattedRuntime(80)).isEqualTo("1h 20min");
	}
	
	@Test
	public void testGetFormattedRuntimeOverOneHourLessThanTenMinutes() {
		assertThat(RandomGenerator.getFormattedRuntime(65)).isEqualTo("1h 05min");
	}
}