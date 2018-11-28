package com.ole.rentalstore.httpclient.unirest.tmdb_api.utils;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieAsExtractedFromTmdbDTO {

	private Integer id;
	@JsonAlias({ "poster_path" })
	private String posterId;
	@JsonAlias({ "backdrop_path" })
	private String bannerId;
	@JsonAlias({ "vote_average" })
	private Double voteAverage;
	@JsonAlias({ "vote_count" })
	private Integer voteCount;
	private String title;
	@JsonAlias({ "release_date" })
	private String releaseDate;
	@JsonAlias({ "genre_ids" })
	private List<Integer> genreIds;
	private String overview;

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

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

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public List<Integer> getGenreIds() {
		return genreIds;
	}

	public void setGenreIds(List<Integer> genreIds) {
		this.genreIds = genreIds;
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
		builder.append("MovieExtractedFromMoviesByGenreServiceDTO [id=");
		builder.append(id);
		builder.append(", posterId=");
		builder.append(posterId);
		builder.append(", bannerId=");
		builder.append(bannerId);
		builder.append(", voteAverage=");
		builder.append(voteAverage);
		builder.append(", title=");
		builder.append(title);
		builder.append(", releaseDate=");
		builder.append(releaseDate);
		builder.append(", genreIds=");
		builder.append(genreIds);
		builder.append(", overview=");
		builder.append(overview);
		builder.append("]");
		return builder.toString();
	}
}