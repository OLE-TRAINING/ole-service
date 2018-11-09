package com.ole.rentalstore.commons.dto.tmdb_api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GenreResponseDTO {

	private List<GenreDTO> genres;

	public void setGenres(List<GenreDTO> genres) {
		this.genres = genres;
	}

	public List<GenreDTO> getGenres() {
		return genres;
	}
	
	public void addGenre(GenreDTO genre) {
		genres.add(genre);
	}
}