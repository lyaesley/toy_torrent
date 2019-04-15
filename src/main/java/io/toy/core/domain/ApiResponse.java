package io.toy.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApiResponse<T> {
	
	private String message;
	private T result;
	
	public ApiResponse(String message, T result) {
        this.message = message;
        this.result = result;
    }

}
