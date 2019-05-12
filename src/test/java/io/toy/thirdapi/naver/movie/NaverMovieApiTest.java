package io.toy.thirdapi.naver.movie;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import io.toy.thirdapi.naver.movie.domain.NaverMovie;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class NaverMovieApiTest {
	
	static {
        System.setProperty("SERVICE_MODE", "dev");
    }
	
	@Autowired
	NaverMovieApi naverMovieApi;
	
	@Test
	public void getMovieInfo() throws UnsupportedEncodingException {
		
		NaverMovie.Request naverMovie = new NaverMovie.Request();
		
		naverMovie.setQuery("엔드게임");
		naverMovie.setCountry("US");
		naverMovie.setYearfrom(2019);
		naverMovie.setYearto(2019);
		
		ResponseEntity<NaverMovie.Response> movie = naverMovieApi.getMovieInfo(naverMovie);
		
		log.debug("response body: " + movie.getBody());
		
	}

}
