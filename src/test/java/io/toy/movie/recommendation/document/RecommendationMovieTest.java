package io.toy.movie.recommendation.document;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.toy.common.enumeration.Yn;
import io.toy.movie.recommendation.controller.RecommendationMovieRestController;
import io.toy.movie.recommendation.domain.RecommendationMovie;
import io.toy.movie.recommendation.service.RecommendationMovieService;
import io.toy.movie.recommendation.service.dto.RecommendationMovieDto;

@RunWith(SpringRunner.class)
@WebMvcTest(RecommendationMovieRestController.class)
@AutoConfigureRestDocs
public class RecommendationMovieTest {
	
	static {
        System.setProperty("SERVICE_MODE", "dev");
    }
	
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RecommendationMovieService recommendationMovieService;
    
    @Test
    public void save() throws Exception {
    	//given
    	RecommendationMovie recommendationMovie = new RecommendationMovie();
		
		recommendationMovie.setMovieCd("movie01");
		recommendationMovie.setUserId("userid01");
		recommendationMovie.setDelYn(Yn.N);		
		recommendationMovie.setCreId("QA");
		recommendationMovie.setCreDtt(LocalDateTime.now());;
		
		given(recommendationMovieService.save(any(RecommendationMovie.class)))
			.willReturn(recommendationMovie);
		
		//when
		RecommendationMovieDto.Create recommendationMovieDto = new RecommendationMovieDto.Create();
		
		recommendationMovieDto.setMovieCd("movie01");
		recommendationMovieDto.setUserId("userid01");
		
		ResultActions result = this.mockMvc.perform(
                post("/toy/v1/movie/recommendation")
                        .content(objectMapper.writeValueAsString(recommendationMovieDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );
		
		//then
		result
		.andExpect(status().isOk())
		.andDo(document("recommendationMovie-save",
            requestFields(
                    fieldWithPath("movieCd").type(JsonFieldType.STRING).description("영화코드"),
                    fieldWithPath("userId").type(JsonFieldType.STRING).description("사용자 아이디")
                    ),
            responseFields(
            		fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메시지"),
            		fieldWithPath("result.id").type(JsonFieldType.NUMBER).description("아이디"),
            		fieldWithPath("result.movieCd").type(JsonFieldType.STRING).description("영화코드"),
                    fieldWithPath("result.userId").type(JsonFieldType.STRING).description("사용자 아이디"),
                    fieldWithPath("result.delYn").type(JsonFieldType.STRING).description("삭제여부"),
                    fieldWithPath("result.creId").type(JsonFieldType.STRING).description("생성자"),
                    fieldWithPath("result.creDtt").type(JsonFieldType.STRING).description("생성일시"),
                    fieldWithPath("result.updId").type(JsonFieldType.NULL).description("수정자"),
                    fieldWithPath("result.updDtt").type(JsonFieldType.NULL).description("수정일시")
            )
		));
		
    }


}
