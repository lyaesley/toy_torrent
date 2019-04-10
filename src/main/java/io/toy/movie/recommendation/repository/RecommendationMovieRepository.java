package io.toy.movie.recommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.toy.movie.recommendation.domain.RecommendationMovie;

public interface RecommendationMovieRepository extends JpaRepository<RecommendationMovie, Long>{

}
