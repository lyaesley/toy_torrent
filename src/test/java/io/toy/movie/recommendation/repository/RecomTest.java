package io.toy.movie.recommendation.repository;

import io.toy.common.enumeration.Yn;
import io.toy.movie.recommendation.domain.RecommendationMovie;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RecomTest {

	@Autowired
	RecommendationMovieRepository recommendationMovieRepository;


	@Test
	public void save() {

		RecommendationMovie recommendationMovie = new RecommendationMovie();

		recommendationMovie.setMovieCd("020202");
		recommendationMovie.setUserId("userId");
		recommendationMovie.setDelYn(Yn.N);
		recommendationMovie.setCreId("QA");

		log.info(recommendationMovie.toString());
		RecommendationMovie rsRecommendationMovie = recommendationMovieRepository.save(recommendationMovie);

		assertEquals(recommendationMovie.getMovieCd(), rsRecommendationMovie.getMovieCd());
		assertEquals(recommendationMovie.getUserId(), rsRecommendationMovie.getUserId());
		assertEquals(recommendationMovie.getDelYn(), rsRecommendationMovie.getDelYn());
		assertEquals(recommendationMovie.getCreId(), rsRecommendationMovie.getCreId());

	}
}
