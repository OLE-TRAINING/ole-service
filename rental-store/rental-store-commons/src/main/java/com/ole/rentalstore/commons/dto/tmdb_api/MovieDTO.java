package com.ole.rentalstore.commons.dto.tmdb_api;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDTO {

	private Integer id;
	private String posterId;
	private String bannerId;
	private Double voteAverage;
	private Integer voteCount;
	private String title;
	private Integer year;
	private List<String> genreNames;
	private String runtime;
	private String overview;
	private boolean favorit;
	private BigDecimal price;
	private boolean acquired;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPosterId() {
		return posterId;
	}

	public void setPosterId(String posterId) {
		this.posterId = posterId;
	}

	public String getBannerId() {
		return bannerId;
	}

	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}

	public Double getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(Double voteAverage) {
		this.voteAverage = voteAverage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<String> getGenreNames() {
		return genreNames;
	}

	public void setGenreNames(List<String> genreNames) {
		this.genreNames = genreNames;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public boolean isFavorit() {
		return favorit;
	}

	public void setFavorit(boolean favorit) {
		this.favorit = favorit;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public boolean isAcquired() {
		return acquired;
	}

	public void setAcquired(boolean acquired) {
		this.acquired = acquired;
	}

	public Integer getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MovieDTO [id=");
		builder.append(id);
		builder.append(", posterId=");
		builder.append(posterId);
		builder.append(", bannerId=");
		builder.append(bannerId);
		builder.append(", voteAverage=");
		builder.append(voteAverage);
		builder.append(", title=");
		builder.append(title);
		builder.append(", year=");
		builder.append(year);
		builder.append(", genreNames=");
		builder.append(genreNames);
		builder.append(", runtime=");
		builder.append(runtime);
		builder.append(", overview=");
		builder.append(overview);
		builder.append(", favorit=");
		builder.append(favorit);
		builder.append(", price=");
		builder.append(price);
		builder.append(", acquired=");
		builder.append(acquired);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		MovieDTO other = (MovieDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}