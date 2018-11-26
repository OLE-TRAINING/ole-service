package com.ole.rentalstore.business.mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ole.rentalstore.commons.dto.tmdb_api.GenreDTO;
import com.ole.rentalstore.commons.dto.tmdb_api.MovieDTO;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.util.MovieAsExtractedFromTmdbDTO;

public abstract class MovieMapperDecorator implements MovieMapper {

	@Autowired
	@Qualifier("delegate")
	private MovieMapper delegate;
	
	private List<String> parseGenreIdToGenreNames(List<Integer> genreIds, List<GenreDTO> genreList) {
		List<String> genreNames = new ArrayList<>();
		for (Integer genreId : genreIds) {
			for (GenreDTO genre : genreList) {
				if (genreId.equals(genre.getId())) {
					genreNames.add(genre.getName());
				}
			}
		}
		return genreNames;
	}
	
	private String filterImageId(String imagePath) {
		int index = imagePath.indexOf('.');
		return imagePath.substring(1, index);
	}
	
	@Override
	public MovieDTO movieExtractedFromMoviesByGenreServiceDTOToMovieDTO(MovieAsExtractedFromTmdbDTO movie, List<GenreDTO> genreList) {
		MovieDTO dto = delegate.movieExtractedFromMoviesByGenreServiceDTOToMovieDTO(movie, genreList);
		if (!StringUtils.isEmpty(movie.getReleaseDate())) {
			dto.setYear(LocalDate.parse(movie.getReleaseDate()).getYear());
		}
		if (!StringUtils.isEmpty(dto.getPosterId())) {
			dto.setPosterId(filterImageId(movie.getPosterId()));
		}
		if (!StringUtils.isEmpty(dto.getBannerId())) {
			dto.setBannerId(filterImageId(movie.getBannerId()));
		}
		dto.setGenreNames(parseGenreIdToGenreNames(movie.getGenreIds(), genreList));
		return dto;
	}
}