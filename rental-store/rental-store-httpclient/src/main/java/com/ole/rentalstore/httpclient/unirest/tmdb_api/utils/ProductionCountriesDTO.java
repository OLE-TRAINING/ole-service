package com.ole.rentalstore.httpclient.unirest.tmdb_api.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductionCountriesDTO {

	private String name;
	
	public ProductionCountriesDTO() {
		
	}
	
	public ProductionCountriesDTO(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}