package io.toy.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
public class UriTest {

    public static void main(String[] args) {
//        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("https://openapi.naver.com/v1/search/movie.json").queryParam("query", "어벤져스").encode();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("https://openapi.naver.com/v1/search/movie.json").queryParam("query", "어벤져스");
        log.info("uri 1 : {}",builder.toUriString());
        log.info("uri 2 : {}",builder.build().toUriString());
        log.info("uri 22 : {}",builder.build().encode().toUriString());
        log.info("uri 3 : {}",builder.build().toUri());
        log.info("uri 4 : {}",builder.build().toString());

    }
}
