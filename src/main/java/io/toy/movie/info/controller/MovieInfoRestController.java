package io.toy.movie.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.toy.thirdapi.naver.movie.NaverMovieApi;

@RequestMapping("toy/v1/movie")
@RestController
public class MovieInfoRestController {
	
	@Autowired
	NaverMovieApi naverMovieApi;

}
