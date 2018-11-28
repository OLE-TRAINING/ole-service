package com.ole.rentalstore.business.mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ole.rentalstore.commons.dto.tmdb_api.CrewMemberDTO;
import com.ole.rentalstore.commons.dto.tmdb_api.GenreDTO;
import com.ole.rentalstore.commons.dto.tmdb_api.MovieDTO;
import com.ole.rentalstore.commons.dto.tmdb_api.MovieDetailedDTO;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.utils.MovieAsExtractedFromTmdbDTO;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.utils.MovieDetailedAsTmdbResponseDTO;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.utils.ProductionCountriesDTO;

public abstract class MovieMapperDecorator implements MovieMapper {

	@Autowired
	@Qualifier("delegate")
	private MovieMapper delegate;

	@Override
	public MovieDTO movieExtractedFromMoviesByGenreServiceDTOToMovieDTO(MovieAsExtractedFromTmdbDTO movie,
			List<GenreDTO> genreList) {
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

	@Override
	public MovieDetailedDTO movieDetailedAsTmdbResponseDTOToMovieDetailedDTO(
			MovieDetailedAsTmdbResponseDTO movieDetailed, List<GenreDTO> genreList) {
		
		MovieDetailedDTO dto = delegate.movieDetailedAsTmdbResponseDTOToMovieDetailedDTO(movieDetailed, genreList);
		dto.setCountries(parseProductionCountriesToStringCountries(movieDetailed.getCountries()));
		dto.setDirectors(getMovieDetailedDTOCrewMembersNameByWorkAs(movieDetailed, "Directing"));		
		dto.setWriters(getMovieDetailedDTOCrewMembersNameByWorkAs(movieDetailed, "Writing"));
		setMovieDetailedDTOCustomizedAttributesFromMovieDTO(movieDetailed, dto, genreList);
		return dto;
	}
	
	private List<String> getMovieDetailedDTOCrewMembersNameByWorkAs(MovieDetailedAsTmdbResponseDTO movieDetailed, String department) {
		List<String> names = new ArrayList<>();
		for (CrewMemberDTO crewMember : movieDetailed.getCredits().getCrew()) {
			if (crewMember.getDepartment().equals(department)) {
				names.add(crewMember.getName());
			}
		}
		return names;
	}

	private void setMovieDetailedDTOCustomizedAttributesFromMovieDTO(MovieDetailedAsTmdbResponseDTO movieDetailed,
			MovieDetailedDTO dto, List<GenreDTO> genreList) {
		MovieDTO movieDTO = movieExtractedFromMoviesByGenreServiceDTOToMovieDTO(movieDetailed, genreList);
		dto.setAcquired(movieDTO.isAcquired());
		dto.setFavorit(movieDTO.isFavorit());
		dto.setGenreNames(movieDTO.getGenreNames());
		dto.setYear(movieDTO.getYear());
		dto.setPosterId(movieDTO.getPosterId());
		dto.setBannerId(movieDTO.getBannerId());
	}

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

	private List<String> parseProductionCountriesToStringCountries(List<ProductionCountriesDTO> countries) {
		List<String> countriesParsed = new ArrayList<>();
		for (ProductionCountriesDTO country : countries) {
			countriesParsed.add(country.getName());
		}
		return countriesParsed;
	}
}