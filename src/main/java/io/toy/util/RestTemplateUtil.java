package io.toy.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class RestTemplateUtil {

	@Value("${api.naver.movie.clientId}")
	String API_NAVER_MOVIE_CLIENTID;
	@Value("${api.naver.movie.clientSecret}")
	String API_NAVER_MOVIE_CLIENTSECRET;
	@Value("${api.naver.movie.sendUrl}")
	String API_NAVER_MOVIE_SENDURL;

	URI url;
	final Map<String, String> header = new HashMap<>();

	public RestTemplateUtil setHeader(String key, String val) {
		header.put(key,val);
		return this;
	}




}
