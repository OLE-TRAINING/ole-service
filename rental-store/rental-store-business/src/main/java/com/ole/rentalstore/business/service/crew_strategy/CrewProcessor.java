package com.ole.rentalstore.business.service.crew_strategy;

import java.util.ArrayList;
import java.util.List;

import com.ole.rentalstore.commons.dto.tmdb_api.CrewMemberDTO;

public class CrewProcessor {

	private CrewProcessor() {
		
	}
	
	public static boolean crewMemberWorksAs(CrewMemberDTO crewMember, String workAs) {
		CrewStrategy crewStrategy = CrewStrategyFactory.getCrewStrategyByCrewMemberWorkAs(workAs);
		return crewStrategy.crewMemberWorksAs(crewMember, workAs);
	}
	
	public static List<CrewMemberDTO> filterCrewMembers(List<CrewMemberDTO> crew, String[] workAs) {
		List<CrewMemberDTO> crewFiltered = new ArrayList<>();
		for (CrewMemberDTO crewMember: crew) {
			for (String work : workAs) {
				if (crewMemberWorksAs(crewMember, work)) {
					crewFiltered.add(crewMember);
					break;
				}
			}
		}
		return crewFiltered;
	}
}