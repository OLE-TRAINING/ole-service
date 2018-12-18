package com.ole.rentalstore.business.service.crew_strategy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class CrewStrateyFactoryTest {

	@Test
	public void testGetCrewStrategyByCrewMemberWorkAsSuccess() {
		assertThat(CrewStrategyFactory.getCrewStrategyByCrewMemberWorkAs("Director")).isNotNull();
	}
	
	@Test
	public void testGetCrewStrategyByCrewMemberWorkAsNull() {
		assertThat(CrewStrategyFactory.getCrewStrategyByCrewMemberWorkAs("Does not exist")).isNull();
	}
}