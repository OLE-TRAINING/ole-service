package com.ole.rentalstore.business.service.crew_strategy;

import com.ole.rentalstore.commons.dto.tmdb_api.CrewMemberDTO;

// some crew members are obtained by the department and others by job (both attributes from the api's response payload)
public interface CrewStrategy {

	boolean crewMemberWorksAs(CrewMemberDTO crewMember, String key);
}
