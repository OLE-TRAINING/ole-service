package com.ole.rentalstore.httpclient.unirest.tmdb_api.utils;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieAsTmdbResponseDTO {

	@JsonAlias({ "total_results" })
	private Integer totalMovies;
	@JsonAlias({ "total_pages" })
	private Integer totalPages;
	private List<MovieAsExtractedFromTmdbDTO> results;

	public List<MovieAsExtractedFromTmdbDTO> getResults() {
		return results;
	}

	public void setResults(List<MovieAsExtractedFromTmdbDTO> movies) {
		this.results = movies;
	}

	public Integer getTotalMovies() {
		return totalMovies;
	}

	public void setTotalMovies(Integer totalMovies) {
		this.totalMovies = totalMovies;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((results == null) ? 0 : results.hashCode());
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
		MovieAsTmdbResponseDTO other = (MovieAsTmdbResponseDTO) obj;
		if (results == null) {
			if (other.results != null)
				return false;
		} else if (!results.equals(other.results))
			return false;
		return true;
	}
}