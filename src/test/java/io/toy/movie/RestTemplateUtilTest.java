package io.toy.movie;

import io.toy.util.RestTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTemplateUtilTest {

    @Value("${api.naver.movie.clientId}")
    String API_NAVER_MOVIE_CLIENTID;
    @Value("${api.naver.movie.clientSecret}")
    String API_NAVER_MOVIE_CLIENTSECRET;
    @Value("${api.naver.movie.sendUrl}")
    String API_NAVER_MOVIE_SENDURL;

    @Test
    public void test() throws URISyntaxException {
        RestTemplateUtil restTemplateUtil = new RestTemplateUtil();
        ResponseEntity responseEntity = restTemplateUtil.setUri(new URI(API_NAVER_MOVIE_SENDURL))
                .setReadTimeout(1)
                .setConnectTimeout(2000)
                .setHeader("X-Naver-Client-Id", API_NAVER_MOVIE_CLIENTID)
                .setHeader("X-Naver-Client-Secret", API_NAVER_MOVIE_CLIENTSECRET)
                .addParam("query", "어벤져스")
                .setInterceptors()
                .get();

        log.info("결과 : {}", responseEntity.toString());
    }
}
