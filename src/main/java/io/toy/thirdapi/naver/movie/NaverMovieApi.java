package io.toy.thirdapi.naver.movie;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import io.toy.common.resttemplate.RequestResponseLoggingInterceptor;
import io.toy.common.resttemplate.RestTemplateResponseErrorHandler;
import io.toy.thirdapi.naver.movie.domain.NaverMovie;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NaverMovieApi {
	
	private RestTemplate restTemplate;

	@Autowired
    public NaverMovieApi(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
          .setConnectTimeout(Duration.ofSeconds(5))
          .errorHandler(new RestTemplateResponseErrorHandler())
          .interceptors(new RequestResponseLoggingInterceptor())
          .build();
    }

	public ResponseEntity<String> getMovieInfo(NaverMovie naverMovie) throws UnsupportedEncodingException {
		
		ResponseEntity<String> result = new ResponseEntity<String>(HttpStatus.OK);

		URI builder = UriComponentsBuilder.newInstance()
				.scheme("https").host("openapi.naver.com")
				.path("/v1/search/movie.json")
			    .queryParam("display", "1")
			    .queryParam("query", naverMovie.getQuery())
			    .queryParam("country", naverMovie.getCountry())
			    .queryParam("yearfrom", naverMovie.getYearfrom())
			    .queryParam("yearto", naverMovie.getYearto())
			    .build().encode().toUri();
		
		//RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-Naver-Client-Id", "GxEYANrgHRakHoImu4ck");
		headers.add("X-Naver-Client-Secret", "ws2Lh9IBQq");
		
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		try {
			result = restTemplate.exchange(builder, HttpMethod.GET, entity, String.class);	
		} catch (ResourceAccessException e) {
			//response = new ApiResponse<>("server connect fail", "server 에 연결 실패");
			//responseEntiry = new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			log.debug("server connect fail: " + e.getCause());
		}
		
		
		return result;
	}

}
