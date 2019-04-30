package io.toy.movie.recommendation.service.dto;

import io.toy.movie.recommendation.domain.RecommendationMovie;
import lombok.Getter;
import lombok.Setter;

public class RecommendationMovieDto {

	@Getter
	@Setter
	public static class Create {
		
		private String movieCd;
		private String userId;
		
		public RecommendationMovie toEntity() {
			
			return RecommendationMovie.builder()
										.movieCd(movieCd)
										.userId(userId)
										.build()
										;
			
		}
		
	}
	
}
