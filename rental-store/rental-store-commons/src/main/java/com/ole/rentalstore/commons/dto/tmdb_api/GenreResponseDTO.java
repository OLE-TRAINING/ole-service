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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genres == null) ? 0 : genres.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenreResponseDTO other = (GenreResponseDTO) obj;
		if (genres == null) {
			if (other.genres != null)
				return false;
		} else if (!genres.equals(other.genres))
			return false;
		return true;
	}
}