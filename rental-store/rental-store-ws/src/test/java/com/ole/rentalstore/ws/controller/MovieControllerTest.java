package com.ole.rentalstore.ws.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ole.rentalstore.business.service.MovieService;
import com.ole.rentalstore.commons.dto.tmdb_api.MovieDetailedDTO;
import com.ole.rentalstore.commons.dto.tmdb_api.MovieResponseDTO;

@RunWith(MockitoJUnitRunner.Silent.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest extends BaseControllerTest {
	
	@Mock
	private MovieService movieService;

	@InjectMocks
	private MovieController movieController;
	
	private static MovieResponseDTO movieResponse = getMovieResponse();
	private static String movieResponseJson = getMovieResponseJson();
	private static MovieDetailedDTO movieDetailed = getMovieDetailed();
	private static String movieDetailedJson = getMovieDetailedJson();
	
	@Before
	public void setUp() throws JsonProcessingException {
		setUp(movieController);
	}
	
	private static MovieDetailedDTO getMovieDetailed() {
		MovieDetailedDTO movieDetailed = new MovieDetailedDTO();
		movieDetailed.setId(1);
		return movieDetailed;
	}
	
	private static String getMovieDetailedJson() {
		try {
			return new ObjectMapper().writeValueAsString(movieDetailed);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static MovieResponseDTO getMovieResponse() {
		MovieResponseDTO movieResponse = new MovieResponseDTO();
		movieResponse.setPage(10);
		movieResponse.setTotalMovies(3);
		movieResponse.setTotalPages(10);
		return movieResponse;
	}
	
	private static String getMovieResponseJson() {
		try {
			return new ObjectMapper().writeValueAsString(movieResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Test
	public void testGetMovies() throws Exception {
		when(movieService.getMovies(anyString(), anyInt(), anyInt(), anyString())).thenReturn(movieResponse);
		mockMvc.perform(get("/movies?amount=10&page=10&filter=genre&filter_id=12"))
				.andExpect(status().isOk())
				.andExpect(content().json(movieResponseJson));
	}
	
	@Test
	public void testGetMovieDetailsSuccess() throws Exception {
		when(movieService.getMovieDetails(anyInt())).thenReturn(movieDetailed);
		mockMvc.perform(get("/movies/{id}/detail".replace("{id}", String.valueOf(1))))
				.andExpect(status().isOk())
				.andExpect(content().json(movieDetailedJson));	
	}
}