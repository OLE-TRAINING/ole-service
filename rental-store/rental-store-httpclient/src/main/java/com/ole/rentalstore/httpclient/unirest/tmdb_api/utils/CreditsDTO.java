package com.ole.rentalstore.httpclient.unirest.tmdb_api.utils;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ole.rentalstore.commons.dto.tmdb_api.CrewMemberDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditsDTO {

	private List<CrewMemberDTO> crew;

	public List<CrewMemberDTO> getCrew() {
		return crew;
	}

	public void setCrew(List<CrewMemberDTO> crew) {
		this.crew = crew;
	}
}