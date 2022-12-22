package com.devsuperior.movieflix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;

public interface ReviewRepository extends JpaRepository<Movie, Long> {

//	@Query("SELECT DISTINCT obj FROM Movie obj WHERE "
//	        + "(obj.genre = :genre OR COALESCE(:genre) IS NULL) "
//	        + "ORDER BY obj.title ASC")
//	Page<Movie> find(Genre genre, Pageable pageable);
//	
//	@Query("SELECT obj FROM Movie obj JOIN FETCH obj.genre WHERE obj IN :movies")
//	List<Movie> findMovieWithGenre(List<Movie> movies);	
	
	
	@Query("SELECT obj FROM Review obj WHERE obj.movie = :movie")
	List<Review> find(Movie movie);
}
