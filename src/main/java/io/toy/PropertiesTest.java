package io.toy;

import javax.annotation.PostConstruct;

import io.toy.movie.recommendation.domain.RecommendationMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesTest {
	@Value("${core}")
    private String core;
    @Value("${test}")
    private String test;

    @PostConstruct
    private void method() {
        System.out.println("--------------------------------");
        System.out.println(core);
        System.out.println("--------------------------------");
        System.out.println(test);
        System.out.println("--------------------------------");

//        recommendationMovie.setCreId("123");

    }
}
