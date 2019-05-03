package io.toy.util;

import io.toy.system.RequestResponseLoggingInterceptor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
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
import java.util.Map;

/**
 * @author 이준영
 * @since 2019. 05. 01
 */
public class RestTemplateUtil {

	private URI uri;
	private static final MultiValueMap param = new LinkedMultiValueMap();
	private static final HttpHeaders headers = new HttpHeaders();
	private static final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	private HttpClient httpClient;
	private RestTemplate restTemplate;
	private int connectTimeout;
	private int readTimeout;
	private ResponseEntity responseEntity;

	public RestTemplateUtil(URI uri) {
		restTemplate = new RestTemplate();
		this.uri = uri;
	}

	public RestTemplateUtil setInterceptors() {
		this.restTemplate = new RestTemplateBuilder()
				.interceptors(new RequestResponseLoggingInterceptor())
				.build();
		return this;
	}

	public RestTemplateUtil setConnectionPool(int maxConnTotal, int maxConnPerRoute) {
		this.httpClient = HttpClientBuilder.create()
				.setMaxConnTotal(maxConnTotal) // connection pool 적용
				.setMaxConnPerRoute(maxConnPerRoute) // connection pool 적용
				.build();
		return this;
	}

	public RestTemplateUtil setHeader(String key, String val) {
		headers.add(key, val);
		return this;
	}

	public RestTemplateUtil addParam(String key, String val) {
		param.add(key, val);
		return this;
	}

	public RestTemplateUtil setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
		return this;
	}

	public RestTemplateUtil setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
		return this;
	}

	public void reset() throws Exception {
		param.clear();
		headers.clear();
		requestFactory.destroy();
	}

	public RestResult<String> get() {
		RestResult<String> res = new RestResult<>(-1, "NONE", null);

		responseEntity = null;

		if (httpClient != null) {
			requestFactory.setHttpClient(httpClient);
		}

		if (connectTimeout > 0) {
			requestFactory.setConnectTimeout(connectTimeout);
		}

		if (readTimeout > 0) {
			requestFactory.setReadTimeout(readTimeout);
		}

		if (param.size() > 0) {
			try {
				restTemplate.setRequestFactory(requestFactory);
				uri = UriComponentsBuilder.fromUri(uri).queryParams(param).build().encode().toUri();
			} catch (Exception e) {
				res.setStatus(-1);
				res.setMessage(e.toString());
				return res;
			}
		}

		try {
			responseEntity = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers), String.class);
			res.setData(String.valueOf(responseEntity.getBody()));
			res.setStatus(responseEntity.getStatusCodeValue());
		} catch (Exception e) {
			try {
				res.setStatus(responseEntity.getStatusCodeValue());
			} catch (Exception ex) {
				res.setStatus(-1);
			}
			res.setMessage(e.toString());
		}

		return res;
	}

	public RestResult<Map<String, Object>> getJsonMap() {
		RestResult<String> json = get();
		RestResult<Map<String, Object>> rv = new RestResult<>();
		rv.setStatus(json.getStatus());
		rv.setMessage(json.getMessage());
		String data = json.getData();

		if (data != null && data.length() > 2) {
			try {
				rv.setData(ConvUtil.toMapByJsonObject(data));
			} catch (Exception e) {
				rv.setStatus(-1);
				rv.setMessage("json 형태의 데이터가 아닙니다. 원본 : " + data);
			}

		} else {
			rv.setData(null);
		}
		return rv;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public class RestResult<T> {
		int status;
		String message;
		T data;

		public boolean isSuccess() {
			return status == 200;
		}

	}

}
