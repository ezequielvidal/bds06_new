package com.devsuperior.movieflix.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.ReviewWithUserDTO;
import com.devsuperior.movieflix.services.ReviewService;

@RestController
@RequestMapping(value = "/movies")
public class ReviewResource {

	@Autowired
	private ReviewService service;
	
	@GetMapping(value = "/{id}/reviews")
	public ResponseEntity<List<ReviewWithUserDTO>> findAll(@PathVariable Long id) {
		List<ReviewWithUserDTO> list = service.findAll(id);
		return ResponseEntity.ok().body(list);
	}
}
