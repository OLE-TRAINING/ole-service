package com.ole.rentalstore.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ole.rentalstore.business.mapper.MovieMapper;

@Service
public class MovieService {

	@Autowired
	private MovieMapper movieMapper;
	
	//public MovieResponseDTO getMoviesByName(String name, Integer page, Integer amount) {
		
	//}
}
