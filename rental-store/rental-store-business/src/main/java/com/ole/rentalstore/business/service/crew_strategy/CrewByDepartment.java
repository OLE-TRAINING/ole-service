package com.ole.rentalstore.business.service.crew_strategy;

import com.ole.rentalstore.commons.dto.tmdb_api.CrewMemberDTO;

public class CrewByDepartment implements CrewStrategy {

	@Override
	// key = name of the department
	public boolean crewMemberWorksAs(CrewMemberDTO crewMember, String key) {
		return crewMember.getDepartment().equals(key);
	}
}