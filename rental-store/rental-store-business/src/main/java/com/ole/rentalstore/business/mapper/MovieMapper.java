package com.ole.rentalstore.business.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ole.rentalstore.commons.dto.tmdb_api.GenreDTO;
import com.ole.rentalstore.commons.dto.tmdb_api.MovieDTO;
import com.ole.rentalstore.commons.dto.tmdb_api.MovieDetailedDTO;
import com.ole.rentalstore.commons.dto.tmdb_api.MovieResponseDTO;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.utils.MovieAsExtractedFromTmdbDTO;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.utils.MovieAsTmdbResponseDTO;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.utils.MovieDetailedAsTmdbResponseDTO;

@Mapper(componentModel = "spring")
@DecoratedWith(MovieMapperDecorator.class)
public interface MovieMapper {

	MovieDTO movieExtractedFromMoviesByGenreServiceDTOToMovieDTO(MovieAsExtractedFromTmdbDTO movie,
			List<GenreDTO> genreList);
	
	
	@Mapping(target = "countries", ignore=true)
	@Mapping(source = "movieDetailed.credits.crew", target = "crew")
	MovieDetailedDTO movieDetailedAsTmdbResponseDTOToMovieDetailedDTO(MovieDetailedAsTmdbResponseDTO movieDetailed, List<GenreDTO> genreList);

	default MovieResponseDTO movieAsTmdbResponseDTOToMovieResponseDTO(
			MovieAsTmdbResponseDTO movieTmdbResponse, List<GenreDTO> genreList) {
		
		MovieResponseDTO movieResponse = new MovieResponseDTO();
		movieResponse.setTotalMovies(movieTmdbResponse.getTotalMovies());
		movieResponse.setTotalPages(movieTmdbResponse.getTotalPages());
		List<MovieDTO> movies = new ArrayList<>();
		for (MovieAsExtractedFromTmdbDTO movie : movieTmdbResponse.getResults()) {
			movies.add(movieExtractedFromMoviesByGenreServiceDTOToMovieDTO(movie, genreList));
		}
		movieResponse.setResults(movies);
		return movieResponse;
	}
}