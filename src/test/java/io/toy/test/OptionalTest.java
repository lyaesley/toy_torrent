package io.toy.test;

import java.util.Optional;

public class OptionalTest {

	public static void main(String[] args) {
		Optional<String> a = Optional.empty();

		a.orElse("결과는?");

		System.out.println(a);
		System.out.println(a.orElse("결과는??"));

	}
}
