package io.toy.movie.recommendation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.toy.movie.recommendation.domain.RecommendationMovie;
import io.toy.movie.recommendation.repository.RecommendationMovieRepository;

@Service
public class RecommendationMovieService {
	
	@Autowired
	RecommendationMovieRepository recommendationMovieRepository;
	
	public RecommendationMovie save(RecommendationMovie recommendationMovie) {
		
		return recommendationMovieRepository.save(recommendationMovie);
		
	}

}
