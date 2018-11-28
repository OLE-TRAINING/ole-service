package com.ole.rentalstore.business.service.crew_strategy;

import com.ole.rentalstore.commons.dto.tmdb_api.CrewMemberDTO;

public class CrewByJob implements CrewStrategy {

	@Override
	// key = name of the job
	public boolean crewMemberWorksAs(CrewMemberDTO crewMember, String key) {
		return crewMember.getJob().equals(key);
	}
}