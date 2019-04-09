package toy.movie.recommendation.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.toy.movie.recommendation.domain.RecommendationMovie;
import io.toy.movie.recommendation.repository.RecommendationMovieRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class RecommendationMovieRepositoryTest {
	
	static {
        System.setProperty("SERVICE_MODE", "dev");
    }
	
	@Autowired
	private RecommendationMovieRepository recommendationMovieRepository;
	
	@Test
	public void save() {
		
		RecommendationMovie recommendationMovie = new RecommendationMovie();
		
		recommendationMovie.setMovieCd("movie_code_123");
		recommendationMovie.setUserId("rhs");
		recommendationMovie.setCreId("QA");
		
		RecommendationMovie rsRecommendationMovie = recommendationMovieRepository.save(recommendationMovie);
		
		assertEquals(recommendationMovie.getMovieCd(), rsRecommendationMovie.getMovieCd());
		assertEquals(recommendationMovie.getUserId(), rsRecommendationMovie.getUserId());
		assertEquals(recommendationMovie.getCreId(), rsRecommendationMovie.getCreId());
		
		
	}
	
	@Test
	public void test() {
		System.out.println("test");
	}

}
