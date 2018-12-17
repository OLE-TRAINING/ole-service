package com.ole.rentalstore.httpclient.unirest.tmdb_api.unirest;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ole.rentalstore.commons.dto.tmdb_api.GenreResponseDTO;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.GenreRequests;

public class GenreRequestsTest extends BaseRequestsTest {
	
	@BeforeClass
	public static void setUp() {
		BaseRequestsTest.setUp();
	}
	
	@Test
	public void testGetGenresList() throws JsonParseException, JsonMappingException, IOException {
	    assertThat(GenreRequests.getGenresList()).isEqualTo(new ObjectMapper().readValue(getGenresJson, GenreResponseDTO.class));
	}
}