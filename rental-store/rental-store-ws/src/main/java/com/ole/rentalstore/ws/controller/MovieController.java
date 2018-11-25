package com.ole.rentalstore.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ole.rentalstore.business.service.MovieService;
import com.ole.rentalstore.commons.dto.tmdb_api.MovieResponseDTO;

@RestController
public class MovieController {
	
	@Autowired
	private MovieService movieService;

	@GetMapping("{filter_id}/movies")
	public ResponseEntity<MovieResponseDTO> getMoviesByGenre(@PathVariable("filter_id") Integer id,
															 @RequestParam("amount") Integer amount, 
															 @RequestParam("page") Integer page,
															 @RequestParam("filter") String filter) {
		return ResponseEntity.ok(movieService.getMovies(id, page, amount, filter));
	}
}