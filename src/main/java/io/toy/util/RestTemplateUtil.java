package io.toy.util;

import io.toy.system.RequestResponseLoggingInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class RestTemplateUtil {

	private URI uri;
	private final MultiValueMap param = new LinkedMultiValueMap();
	private final HttpHeaders headers = new HttpHeaders();
	private final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	private RestTemplate restTemplate;

	public RestTemplateUtil() {
		restTemplate = new RestTemplate();
	}

	public RestTemplateUtil setUri(URI uri) {
		this.uri = uri;
		return this;
	}

	public RestTemplateUtil setInterceptors() {
		this.restTemplate = new RestTemplateBuilder()
				.interceptors(new RequestResponseLoggingInterceptor())
				.build();
		return this;
	}

	public RestTemplateUtil setHeader(String key, String val) {
		this.headers.add(key,val);
		return this;
	}

	public RestTemplateUtil setConnectTimeout(int connectTimeout) {
		this.requestFactory.setConnectTimeout(connectTimeout);
		return this;
	}

	public RestTemplateUtil setReadTimeout(int readTimeout) {
		this.requestFactory.setConnectTimeout(readTimeout);
		return this;
	}

	public RestTemplateUtil addParam(String key, String val) {
		this.param.add(key,val);
		return this;
	}

	public ResponseEntity get() {

		ResponseEntity responseEntity = null;

		if (param.size() > 0) {
			restTemplate.setRequestFactory(requestFactory);
			uri = UriComponentsBuilder.fromUri(uri).queryParams(param).build().encode().toUri();
		}

		try {
			responseEntity = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers), String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseEntity;
	}

}
