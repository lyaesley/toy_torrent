package io.toy.thirdapi.naver.movie.domain;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class NaverMovie {
	
	@Getter
	@Setter
	public static class Request {
		
		//검색을 원하는 질의. UTF-8 인코딩
		private String query;
		
		//국가 코드. 한국(KR), 미국(US), 일본(JP), 기타(ETC)
		private String country;
		
		//검색 결과 출력 건수
		private int display;
		
		//검색 시작위치
		private int start;
		
		// 장르
		/*
		1: 드라마 2: 판타지
		3: 서부 4: 공포
		5: 로맨스 6: 모험
		7: 스릴러 8: 느와르
		9: 컬트 10: 다큐멘터리
		11: 코미디 12: 가족
		13: 미스터리 14: 전쟁
		15: 애니메이션 16: 범죄
		17: 뮤지컬 18: SF
		19: 액션20: 무협
		21: 에로 22: 서스펜스
		23: 서사 24: 블랙코미디
		25: 실험 26: 영화카툰
		27: 영화음악 28: 영화패러디포스터
		 */
		private String genre;
		
		//제작년도
		private int yearfrom;
		
		//제작년도
		private int yearto;
		
	}
	
	@Getter
	@Setter
	@NoArgsConstructor
	public static class Response {
		
		private String lastBuildDate;
	    private Integer total;
	    private Integer start;
	    private Integer display;
	    private List<Items> items;
		
	}
	
	@Getter
	@Setter
	@NoArgsConstructor
	public class Items{
		
		private String title;
		private String link;
		private String image;
		private String subtitle;
		private String pubDate;
		private String director;
		private String actor;
		private String userRating;
		
	}
	
	

}
