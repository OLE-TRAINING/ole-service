package com.ole.rentalstore.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ole.rentalstore.business.mapper.MovieMapper;
import com.ole.rentalstore.business.service.pagehandler.PageHandler;
import com.ole.rentalstore.business.service.pagehandler.PageSetter;
import com.ole.rentalstore.commons.dto.tmdb_api.GenreDTO;
import com.ole.rentalstore.commons.dto.tmdb_api.GenreResponseDTO;
import com.ole.rentalstore.commons.dto.tmdb_api.MovieDTO;
import com.ole.rentalstore.commons.dto.tmdb_api.MovieResponseDTO;
import com.ole.rentalstore.commons.utils.RandomGenerator;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.GenreRequests;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.exceptions.GenreNotFoundException;
import com.ole.rentalstore.httpclient.unirest.tmdb_api.util.MovieAsTmdbResponseDTO;

@Service
public class GenreService {

	@Autowired
	private MovieMapper movieMapper;

	public GenreResponseDTO getMovieGenres() {
		GenreResponseDTO genres = GenreRequests.getGenresList();
		genres.addGenre(new GenreDTO(-1, "Últimos filmes"));
		return genres;
	}
	
	private void setRandomFields(List<MovieDTO> movies) {
		for (MovieDTO movie : movies) {
			movie.setFavorit(RandomGenerator.getRandomBoolean());
			movie.setAcquired(RandomGenerator.getRandomBoolean());
			movie.setPrice(RandomGenerator.getRandomFormattedPrice());
			movie.setRuntime(RandomGenerator.getRandomFormattedRuntime());
		}
	}
	
	public boolean genreExists(Integer id, List<GenreDTO> genres) {
		for (GenreDTO genre : genres) {
			if (genre.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}

	public MovieResponseDTO getMoviesByGenre(Integer id, Integer page, Integer amount) {
		List<GenreDTO> genres = getMovieGenres().getGenres();
		if (!genreExists(id, genres)) {
			throw new GenreNotFoundException("Genre " + id + " does not exist");
		}
		PageHandler pageHandler = PageSetter.getPageHandlerByAmount(amount);
		Integer appropriatePage = pageHandler.getAppropriatePage(page);
		MovieAsTmdbResponseDTO movieTmdbResponse = GenreRequests.getMoviesListByGenre(id, appropriatePage);
		movieTmdbResponse.setResults(pageHandler.formatResponseList(movieTmdbResponse.getResults(), appropriatePage));
		Integer currentTotalPages = movieTmdbResponse.getTotalPages();
		movieTmdbResponse.setTotalPages(pageHandler.getTotalPages(currentTotalPages));
		
		MovieResponseDTO movieResponse = movieMapper.MovieAsTmdbResponseDTOToMovieResponseDTO(movieTmdbResponse, genres, movieMapper);
		setRandomFields(movieResponse.getResults());
		movieResponse.setPage(page);
		return movieResponse;
	}
}