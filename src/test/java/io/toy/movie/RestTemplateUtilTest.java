package io.toy.movie;

import io.toy.util.RestTemplateUtil;
import io.toy.util.RestTemplateUtil.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTemplateUtilTest {

	@Value("${api.naver.movie.clientId}")
	private String API_NAVER_MOVIE_CLIENTID;
	@Value("${api.naver.movie.clientSecret}")
	private String API_NAVER_MOVIE_CLIENTSECRET;
	@Value("${api.naver.movie.sendUrl}")
	private String API_NAVER_MOVIE_SENDURL;

	private RestTemplateUtil restTemplateUtil;

	@Before
	public void init() throws URISyntaxException {
		log.info("@Before 시작");
		restTemplateUtil = new RestTemplateUtil(new URI(API_NAVER_MOVIE_SENDURL))
				.setReadTimeout(3000)
				.setConnectTimeout(3000)
				.setConnectionPool(3, 3)
				.setHeader("X-Naver-Client-Id", API_NAVER_MOVIE_CLIENTID)
				.setHeader("X-Naver-Client-Secret", API_NAVER_MOVIE_CLIENTSECRET);
	}

	@Test
	public void getSearchMovie() {
		RestResult<String> restResult = restTemplateUtil
                .addParam("query", "어벤져스")
//                .setInterceptors()
				.get();

		log.info("결과 body : {}", restResult.getData());
		log.info("결과 상태 : {}", restResult.getStatus());
		log.info("결과 메시지: {}", restResult.getMessage());
	}

	@Test
	public void getSearchMovie2() {
		RestResult<Map<String, Object>> restResult = restTemplateUtil
				.addParam("query", "마녀")
//                .setInterceptors()
				.getJsonMap();

		log.info("결과 body : {}", restResult.getData());
		log.info("결과 상태 : {}", restResult.getStatus());
		log.info("결과 메시지: {}", restResult.getMessage());
	}

	@After
	public void resetRestTemplateUtil() throws Exception {
		log.info("@After 시작");
		restTemplateUtil.reset();
	}
}
