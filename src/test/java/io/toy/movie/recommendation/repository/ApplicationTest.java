package io.toy.movie.recommendation.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

//테스트에서 spring.profiles.active=core 를 찾아 프로퍼티를 읽어온다.
@ActiveProfiles("core")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

	@Value("${core}")
    private String core;
    @Value("${test}")
    private String test;

    @Test
    public void contextLoads() {
        assertThat(core, is("core"));
        assertThat(test, is("dev"));
    }
    
}
