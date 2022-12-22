package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private UserService userService;
	
	@Transactional(readOnly = true)
	public List<ReviewDTO> findAll(Long movieId) {
		//Movie movie = (movieId == 0) ? null : movieRepository.getOne(movieId);
		List<Review> list = repository.find(movieId);
		//repository.findMovieWithGenre(page.getContent());
		return list.stream().map(x -> new ReviewDTO(x, x.getUser())).collect(Collectors.toList());
	}
	
	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		Review entity = new Review();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ReviewDTO(entity, entity.getUser());
	}
	
	private void copyDtoToEntity(ReviewDTO dto, Review entity) {

		entity.setText(dto.getText());
		
		Movie movie = movieRepository.getOne(dto.getMovieId());
		entity.setMovie(movie);
		
		UserDTO userDTO = userService.findUserLogged();
		
		User user = new User();
		user.setId(userDTO.getId());
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		
		entity.setUser(user);
	}
}
