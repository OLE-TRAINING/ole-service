package com.ole.rentalstore.commons.dto.tmdb_api;

import java.util.List;

public class MovieResponseDTO {

	private Integer totalMovies;
	private Integer totalPages;
	private Integer page;
	private List<MovieDTO> results;

	public List<MovieDTO> getResults() {
		return results;
	}

	public void setResults(List<MovieDTO> movies) {
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

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
}