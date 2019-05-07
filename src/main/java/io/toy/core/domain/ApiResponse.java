package io.toy.core.domain;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
	
	private HttpStatus httpStatus;
	private String message;
	private T result;

}
