package com.ole.rentalstore.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ole.rentalstore.business.service.GenreService;
import com.ole.rentalstore.commons.dto.tmdb_api.GenreResponseDTO;

@RestController
public class GenreController {

	@Autowired
	private GenreService genreService;

	@GetMapping("/genres")
	public ResponseEntity<GenreResponseDTO> getMovieGenres() {
		return ResponseEntity.ok(genreService.getMovieGenres());
	}
}