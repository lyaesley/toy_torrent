package io.toy.movie;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.Utf8Encoder;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieTest {

	@Autowired
	MovieController movieController;
	private MockMvc mockMvc;

	@Value("${api.naver.movie.clientId}")
	String API_NAVER_MOVIE_CLIENTID;
	@Value("${api.naver.movie.clientSecret}")
	String API_NAVER_MOVIE_CLIENTSECRET;
	@Value("${api.naver.movie.sendUrl}")
	String API_NAVER_MOVIE_SENDURL;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
	}

	//	@Test
	public void test() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/movie/test").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void naverMovieApi() throws URISyntaxException, IOException {
		RestTemplate restTemplate = new RestTemplate();

		//타임아웃 설정
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setReadTimeout(3000);
		factory.setConnectTimeout(3000);

		restTemplate.setRequestFactory(factory);

		URI uri = UriComponentsBuilder.fromUriString(API_NAVER_MOVIE_SENDURL).queryParam("query", "어벤져스").build().encode().toUri();

		RequestEntity requestEntity = RequestEntity.get(uri).header("X-Naver-Client-Id", API_NAVER_MOVIE_CLIENTID).header("X-Naver-Client-Secret", API_NAVER_MOVIE_CLIENTSECRET).build();

		ResponseEntity responseEntity = restTemplate.exchange(requestEntity, String.class);

		log.info(responseEntity.toString());

		Map resultMap = new ObjectMapper().readValue(responseEntity.getBody().toString(), Map.class);

		log.info(resultMap.toString());

		log.info(resultMap.get("items").toString());

	}

}
