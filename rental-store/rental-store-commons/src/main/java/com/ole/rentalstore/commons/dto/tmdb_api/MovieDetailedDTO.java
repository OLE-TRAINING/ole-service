package com.ole.rentalstore.commons.dto.tmdb_api;

import java.util.List;

public class MovieDetailedDTO extends MovieDTO {

	private List<CrewMemberDTO> crew;
	private List<String> countries;

	public List<CrewMemberDTO> getCrew() {
		return crew;
	}

	public void setCrew(List<CrewMemberDTO> crew) {
		this.crew = crew;
	}

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}
}