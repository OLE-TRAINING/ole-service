package com.ole.rentalstore.business.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.ole.rentalstore.business.mapper.MovieMapper;
import com.ole.rentalstore.commons.dto.tmdb_api.GenreDTO;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.utils.MovieDetailedAsTmdbResponseDTO;

public class MovieServiceTest {

	@Spy
	private MovieMapper movieMapper = Mappers.getMapper(MovieMapper.class);
	
	@InjectMocks
	private MovieService movieService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testSetRandomFields() {
		MovieDetailedAsTmdbResponseDTO movieDetailed = new MovieDetailedAsTmdbResponseDTO();
		movieDetailed.setGenres(Arrays.asList(new GenreDTO(1, "foo"), new GenreDTO(2, "bar")));
		movieService.setGenreIdsFromGenreDTO(movieDetailed);
		assertThat(movieDetailed.getGenreIds()).isEqualTo(Arrays.asList(1, 2));
	}
}