package com.ole.rentalstore.business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ole.rentalstore.business.mapper.MovieMapper;
import com.ole.rentalstore.business.service.crew_strategy.CrewProcessor;
import com.ole.rentalstore.business.service.page_strategy.PageProcessor;
import com.ole.rentalstore.commons.dto.tmdb_api.GenreDTO;
import com.ole.rentalstore.commons.dto.tmdb_api.MovieDTO;
import com.ole.rentalstore.commons.dto.tmdb_api.MovieDetailedDTO;
import com.ole.rentalstore.commons.dto.tmdb_api.MovieResponseDTO;
import com.ole.rentalstore.commons.utils.RandomGenerator;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.MovieRequests;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.utils.MovieAsTmdbResponseDTO;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.utils.MovieDetailedAsTmdbResponseDTO;

@Service
public class MovieService {

	@Autowired
	private MovieMapper movieMapper;
	@Autowired
	private GenreService genreService;

	public MovieResponseDTO getMovies(String id, Integer page, Integer amount, String filter) {
		List<GenreDTO> genres = genreService.getMovieGenres().getGenres();
		MovieAsTmdbResponseDTO movieTmdbResponse = PageProcessor.getMoviesThreatingPagination(id, amount, page, filter);

		MovieResponseDTO movieResponse = movieMapper.movieAsTmdbResponseDTOToMovieResponseDTO(movieTmdbResponse,
				genres);
		setRandomFieldsOfAllMoviesOnTheList(movieResponse.getResults());
		movieResponse.setPage(page);
		return movieResponse;
	}

	public MovieDetailedDTO getMovieDetails(Integer id) {
		List<GenreDTO> genres = genreService.getMovieGenres().getGenres();
		MovieDetailedAsTmdbResponseDTO movieDetailedAsTmdbResponse = MovieRequests.getMovieDetails(id);
		setGenreIdsFromGenreDTO(movieDetailedAsTmdbResponse);

		String[] workAs = { "Director", "Writing" };
		movieDetailedAsTmdbResponse.getCredits()
				.setCrew(CrewProcessor.filterCrewMembers(movieDetailedAsTmdbResponse.getCredits().getCrew(), workAs));

		MovieDetailedDTO movieDetailed = movieMapper
				.movieDetailedAsTmdbResponseDTOToMovieDetailedDTO(movieDetailedAsTmdbResponse, genres);
		setRandomFields(movieDetailed);
		return movieDetailed;
	}

	// detail service returns genres as an object, not just a list of int or String
	public void setGenreIdsFromGenreDTO(MovieDetailedAsTmdbResponseDTO movieDetailedAsTmdbResponse) {
		List<Integer> genreIds = new ArrayList<>();
		for (GenreDTO genre : movieDetailedAsTmdbResponse.getGenres()) {
			genreIds.add(genre.getId());
		}
		movieDetailedAsTmdbResponse.setGenreIds(genreIds);
	}

	private void setRandomFieldsOfAllMoviesOnTheList(List<MovieDTO> movies) {
		for (MovieDTO movie : movies) {
			setRandomFields(movie);
		}
	}

	private void setRandomFields(MovieDTO movie) {
		movie.setFavorit(RandomGenerator.getRandomBoolean());
		movie.setAcquired(RandomGenerator.getRandomBoolean());
		movie.setPrice(RandomGenerator.getFormattedPrice(RandomGenerator.getRandomPrice()));
		movie.setRuntime(RandomGenerator.getFormattedRuntime(RandomGenerator.getRandomRuntime()));
	}
}