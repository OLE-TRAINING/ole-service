package com.ole.rentalstore.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ole.rentalstore.business.service.GenreService;
import com.ole.rentalstore.commons.dto.tmdb_api.GenreResponseDTO;
import com.ole.rentalstore.commons.dto.tmdb_api.MovieResponseDTO;

@RestController
public class GenreController {

	@Autowired
	private GenreService genreService;

	@GetMapping("genres")
	public ResponseEntity<GenreResponseDTO> getMovieGenres() {
		return ResponseEntity.ok(genreService.getMovieGenres());
	}

	@GetMapping("genres/{id}/movies")
	public ResponseEntity<MovieResponseDTO> getMoviesByGenre(@PathVariable("id") Integer id,
															 @RequestParam("amount") Integer amount, 
															 @RequestParam("page") Integer page) {
		return ResponseEntity.ok(genreService.getMovies(id, page, amount, filter));
	}
}