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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((page == null) ? 0 : page.hashCode());
		result = prime * result + ((results == null) ? 0 : results.hashCode());
		result = prime * result + ((totalMovies == null) ? 0 : totalMovies.hashCode());
		result = prime * result + ((totalPages == null) ? 0 : totalPages.hashCode());
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
		MovieResponseDTO other = (MovieResponseDTO) obj;
		if (page == null) {
			if (other.page != null)
				return false;
		} else if (!page.equals(other.page))
			return false;
		if (results == null) {
			if (other.results != null)
				return false;
		} else if (!results.equals(other.results))
			return false;
		if (totalMovies == null) {
			if (other.totalMovies != null)
				return false;
		} else if (!totalMovies.equals(other.totalMovies))
			return false;
		if (totalPages == null) {
			if (other.totalPages != null)
				return false;
		} else if (!totalPages.equals(other.totalPages))
			return false;
		return true;
	}
}