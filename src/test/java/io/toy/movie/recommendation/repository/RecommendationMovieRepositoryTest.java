package io.toy.movie.recommendation.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.toy.common.enumeration.Yn;
import io.toy.movie.recommendation.domain.RecommendationMovie;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RecommendationMovieRepositoryTest {
	
	static {
        System.setProperty("SERVICE_MODE", "dev");
    }

	@Autowired
	RecommendationMovieRepository recommendationMovieRepository;
	
	@Test
	public void save() {
		
		RecommendationMovie recommendationMovie = new RecommendationMovie();
		
		recommendationMovie.setMovieCd("020202");
		recommendationMovie.setUserId("userId");
		recommendationMovie.setDelYn(Yn.N);
		recommendationMovie.setCreId("QA");
		
		RecommendationMovie rsRecommendationMovie = recommendationMovieRepository.save(recommendationMovie);
		
		assertEquals(recommendationMovie.getMovieCd(), rsRecommendationMovie.getMovieCd());
		assertEquals(recommendationMovie.getUserId(), rsRecommendationMovie.getUserId());
		assertEquals(recommendationMovie.getDelYn(), rsRecommendationMovie.getDelYn());
		assertEquals(recommendationMovie.getCreId(), rsRecommendationMovie.getCreId());
		
		
	}

}
