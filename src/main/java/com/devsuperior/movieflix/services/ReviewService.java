package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Transactional(readOnly = true)
	public List<ReviewDTO> findAll(Long movieId) {
		Movie movie = (movieId == 0) ? null : movieRepository.getOne(movieId);
		List<Review> list = repository.find(movie);
		//repository.findMovieWithGenre(page.getContent());
		return list.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
	}
	
//	@Transactional(readOnly = true)
//	public MovieDTO findById(Long id) {
//		
//		Optional<Movie> obj = repository.findById(id);
//		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
//		return new MovieDTO(entity, entity.getGenre());
//	}
}
