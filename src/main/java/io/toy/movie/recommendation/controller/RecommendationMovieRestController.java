package io.toy.movie.recommendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.toy.core.domain.ApiResponse;
import io.toy.movie.recommendation.domain.RecommendationMovie;
import io.toy.movie.recommendation.service.RecommendationMovieService;
import io.toy.movie.recommendation.service.dto.RecommendationMovieDto;

@RequestMapping("toy/v1/movie")
@RestController
public class RecommendationMovieRestController {
	
	@Autowired
	RecommendationMovieService recommendationMovieService;
	
	@PostMapping("/recommendation")
	public ResponseEntity<Object> save(@RequestBody RecommendationMovieDto.Create recommendationMovie) {
		
		RecommendationMovie rsRecommendationMovie = recommendationMovieService.save(recommendationMovie.toEntity());
		
		ApiResponse<RecommendationMovie> response = new ApiResponse<>("success", rsRecommendationMovie);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
		
	}

}
