package io.toy.movie.recommendation.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.toy.common.enumeration.Yn;
import io.toy.movie.recommendation.domain.RecommendationMovie;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecommendationMovieServiceTest {
	
	static {
        System.setProperty("SERVICE_MODE", "dev");
    }
	
	@Autowired
	RecommendationMovieService recommendationMovieService;
	
	@Test
	public void save() {
		
		RecommendationMovie recommendationMovie = new RecommendationMovie();
		
		recommendationMovie.setMovieCd("movie01");
		recommendationMovie.setUserId("userid01");
		recommendationMovie.setDelYn(Yn.N);
		recommendationMovie.setCreId("QA");
		
		RecommendationMovie rsRecommendationMovie = recommendationMovieService.save(recommendationMovie);
		
		assertEquals(recommendationMovie.getMovieCd(), rsRecommendationMovie.getMovieCd());
		assertEquals(recommendationMovie.getUserId(), rsRecommendationMovie.getUserId());
		assertEquals(recommendationMovie.getDelYn(), rsRecommendationMovie.getDelYn());
		assertEquals(recommendationMovie.getCreId(), rsRecommendationMovie.getCreId());
		
	}
	
	

}
