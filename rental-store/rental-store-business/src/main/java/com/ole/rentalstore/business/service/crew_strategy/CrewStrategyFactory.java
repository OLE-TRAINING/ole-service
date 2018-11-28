package com.ole.rentalstore.business.service.crew_strategy;

import java.util.Arrays;
import java.util.List;

public enum CrewStrategyFactory {

	WORK_ACCORDING_TO_JOB(Arrays.asList("Director"), new CrewByJob()),
	WORK_ACCORDING_TO_DEPARTMENT(Arrays.asList("Writing"), new CrewByDepartment());
	
	private List<String> crewWorkingPosition;
	private CrewStrategy crewStrategy;
	
	private CrewStrategyFactory(List<String> crewWorkingPosition, CrewStrategy crewStrategy) {
		this.crewWorkingPosition = crewWorkingPosition;
		this.crewStrategy = crewStrategy;
	}

	public List<String> getCrewWorkingPosition() {
		return crewWorkingPosition;
	}

	public CrewStrategy getCrewStrategy() {
		return crewStrategy;
	}
	
	public static CrewStrategy getCrewStrategyByCrewMemberWorkAs(String crewMemberWork) {
		for (CrewStrategyFactory crewStragegyFactory : CrewStrategyFactory.values()) {
			for (String workAs : crewStragegyFactory.getCrewWorkingPosition()) {
				if (workAs.equals(crewMemberWork)) {
					return crewStragegyFactory.getCrewStrategy();
				}
			}
		}
		return null;
	}
}