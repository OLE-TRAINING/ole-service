package com.ole.rentalstore.httpclient.unirest.tmdb_api.utils;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ole.rentalstore.commons.dto.tmdb_api.GenreDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDetailedAsTmdbResponseDTO extends MovieAsExtractedFromTmdbDTO {

	private List<GenreDTO> genres;
	@JsonAlias({ "production_countries" })
	private List<ProductionCountriesDTO> countries;
	private CreditsDTO credits;
	
	public List<GenreDTO> getGenres() {
		return genres;
	}

	public void setGenres(List<GenreDTO> genres) {
		this.genres = genres;
	}

	public List<ProductionCountriesDTO> getCountries() {
		return countries;
	}

	public void setCountries(List<ProductionCountriesDTO> countries) {
		this.countries = countries;
	}

	public CreditsDTO getCredits() {
		return credits;
	}

	public void setCredits(CreditsDTO credits) {
		this.credits = credits;
	}
}