package io.toy.movie.recommendation.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.toy.common.enumeration.Yn;
import io.toy.movie.recommendation.domain.RecommendationMovie;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class RecommendationMovieRestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	RecommendationMovieRestController recommendationMovieRestController;
	
	@Test
	public void save() throws Exception {
		
		RecommendationMovie recommendationMovie = new RecommendationMovie();
		
		recommendationMovie.setMovieCd("movie01");
		recommendationMovie.setUserId("userid01");
		recommendationMovie.setDelYn(Yn.N);
		recommendationMovie.setCreId("QA");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String recommendationMovieStr  = objectMapper.writeValueAsString(recommendationMovie);
		
		mockMvc.perform(post("/toy/v1/movie/recommendation")
				.contentType(MediaType.APPLICATION_JSON)
	 			.content(recommendationMovieStr))
			.andDo(print())
			.andExpect(status().is2xxSuccessful())
		;
		
	}
	

}
