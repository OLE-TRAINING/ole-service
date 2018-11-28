package com.ole.rentalstore.commons.dto.tmdb_api;

import java.util.List;

public class MovieDetailedDTO extends MovieDTO {

	private List<String> directors;
	private List<String> writers;
	private List<String> countries;

	public List<String> getDirectors() {
		return directors;
	}

	public void setDirectors(List<String> directors) {
		this.directors = directors;
	}

	public List<String> getWriters() {
		return writers;
	}

	public void setWriters(List<String> writers) {
		this.writers = writers;
	}

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}
}