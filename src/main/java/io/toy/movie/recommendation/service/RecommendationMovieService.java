package io.toy.movie.recommendation.service;

import org.springframework.stereotype.Service;

import io.toy.movie.recommendation.domain.RecommendationMovie;
import io.toy.movie.recommendation.repository.RecommendationMovieRepository;

@Service
public class RecommendationMovieService {
	
	RecommendationMovieRepository recommendationMovieRepository;
	
	public RecommendationMovie save(RecommendationMovie recommendationMovie) {
		
		return recommendationMovieRepository.save(recommendationMovie);
		
	}

}
