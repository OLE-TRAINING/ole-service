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
}