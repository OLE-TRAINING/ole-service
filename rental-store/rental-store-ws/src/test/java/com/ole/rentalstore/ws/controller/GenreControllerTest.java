package com.ole.rentalstore.ws.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ole.rentalstore.business.service.GenreService;
import com.ole.rentalstore.commons.dto.tmdb_api.GenreResponseDTO;

@RunWith(MockitoJUnitRunner.Silent.class)
@WebMvcTest(GenreController.class)
public class GenreControllerTest extends BaseControllerTest {

	@Mock
	private GenreService genreService;

	@InjectMocks
	private GenreController genreController;
	
	private GenreResponseDTO genreResponse = getGenreResponse();

	private static final String genreResponseJson = "{\r\n" + 
			"    \"genres\": [\r\n" + 
			"        {\r\n" + 
			"            \"id\": 28,\r\n" + 
			"            \"name\": \"Ação\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": 12,\r\n" + 
			"            \"name\": \"Aventura\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": 16,\r\n" + 
			"            \"name\": \"Animação\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": 35,\r\n" + 
			"            \"name\": \"Comédia\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": 80,\r\n" + 
			"            \"name\": \"Crime\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": 99,\r\n" + 
			"            \"name\": \"Documentário\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": 18,\r\n" + 
			"            \"name\": \"Drama\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": 10751,\r\n" + 
			"            \"name\": \"Família\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": 14,\r\n" + 
			"            \"name\": \"Fantasia\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": 36,\r\n" + 
			"            \"name\": \"História\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": 27,\r\n" + 
			"            \"name\": \"Terror\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": 10402,\r\n" + 
			"            \"name\": \"Música\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": 9648,\r\n" + 
			"            \"name\": \"Mistério\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": 10749,\r\n" + 
			"            \"name\": \"Romance\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": 878,\r\n" + 
			"            \"name\": \"Ficção científica\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": 10770,\r\n" + 
			"            \"name\": \"Cinema TV\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": 53,\r\n" + 
			"            \"name\": \"Thriller\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": 10752,\r\n" + 
			"            \"name\": \"Guerra\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": 37,\r\n" + 
			"            \"name\": \"Faroeste\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": -1,\r\n" + 
			"            \"name\": \"Últimos filmes\"\r\n" + 
			"        }\r\n" + 
			"    ]\r\n" + 
			"}";

	@Before
	public void setUp() throws JsonParseException, JsonMappingException, IOException {
		setUp(genreController);
	}
	
	private static GenreResponseDTO getGenreResponse() {
		try {
			return new ObjectMapper().readValue(genreResponseJson, GenreResponseDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Test
	public void testGetMovieGenresSuccess() throws Exception {
		when(genreService.getMovieGenres()).thenReturn(genreResponse);
		mockMvc.perform(get("/genres"))
				.andExpect(status().isOk())
				.andExpect(content().json(genreResponseJson));
	}
}