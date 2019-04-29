package io.toy.movie.recommendation.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.toy.common.enumeration.Yn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Data
@Table(name = "recommendation_movie")
public class RecommendationMovie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(length = 50)
	private String movieCd;
	
	@Column(length = 20)
	private String userId;
	
	@Enumerated(EnumType.STRING)
	private Yn delYn;

	private String creId;

	@CreationTimestamp
	private LocalDateTime creDtt;

	private String updId;

	@UpdateTimestamp
	private LocalDateTime updDtt; 

}
