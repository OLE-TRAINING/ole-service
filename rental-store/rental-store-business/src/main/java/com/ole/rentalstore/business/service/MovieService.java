package com.ole.rentalstore.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ole.rentalstore.business.mapper.MovieMapper;
import com.ole.rentalstore.business.service.page_strategy.PageProcessor;
import com.ole.rentalstore.commons.dto.tmdb_api.GenreDTO;
import com.ole.rentalstore.commons.dto.tmdb_api.MovieDTO;
import com.ole.rentalstore.commons.dto.tmdb_api.MovieDetailedDTO;
import com.ole.rentalstore.commons.dto.tmdb_api.MovieResponseDTO;
import com.ole.rentalstore.commons.utils.RandomGenerator;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.MovieRequests;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.util.MovieAsTmdbResponseDTO;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.util.MovieDetailedAsTmdbResponseDTO;

@Service
public class MovieService {

	@Autowired
	private MovieMapper movieMapper;
	@Autowired
	private GenreService genreService;
	
	private void setRandomFields(List<MovieDTO> movies) {
		for (MovieDTO movie : movies) {
			movie.setFavorit(RandomGenerator.getRandomBoolean());
			movie.setAcquired(RandomGenerator.getRandomBoolean());
			movie.setPrice(RandomGenerator.getFormattedPrice(RandomGenerator.getRandomPrice()));
			movie.setRuntime(RandomGenerator.getFormattedRuntime(RandomGenerator.getRandomRuntime()));
		}
	}
	
	private void setRandomFields(MovieDetailedDTO movieDetailed) {
		movieDetailed.setDirector(RandomGenerator.getRandomName());
		movieDetailed.setWriter(RandomGenerator.getRandomName());
	}
	
	public boolean genreExists(Integer id, List<GenreDTO> genres) {
		for (GenreDTO genre : genres) {
			if (genre.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	public MovieResponseDTO getMovies(String id, Integer page, Integer amount, String filter) {
		List<GenreDTO> genres = genreService.getMovieGenres().getGenres();
		MovieAsTmdbResponseDTO movieTmdbResponse = PageProcessor.getMoviesThreatingPagination(id, amount, page, filter);
		
		MovieResponseDTO movieResponse = movieMapper.movieAsTmdbResponseDTOToMovieResponseDTO(movieTmdbResponse, genres);
		setRandomFields(movieResponse.getResults());
		movieResponse.setPage(page);
		return movieResponse;
	}
	
	public MovieDetailedDTO getMovieDetails(Integer id) {
		MovieDetailedAsTmdbResponseDTO movieDetailedAsTmdbResponse = MovieRequests.getMovieDetails(id);
		MovieDetailedDTO movieDetailed = movieMapper.movieDetailedAsTmdbResponseDTOToMovieDetailedDTO(movieDetailedAsTmdbResponse);
		setRandomFields(movieDetailed);
		return movieDetailed;
	}
}