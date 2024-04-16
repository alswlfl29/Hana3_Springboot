package com.study.Ex22TDD;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ApiController.class)
class ApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean // ApiController 에서 주입받은 Bean 객체에 대해서 Mock 형태로 객체 생성
    MemberService memberService;

    @Autowired
    private WebApplicationContext ctx;

    @BeforeEach
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("로그인 테스트")
    void loginAction1() throws Exception{
        // given when
        // loginAction 함수가 제대로 동작하는 조건 기술
        given(memberService.loginAction(new Member("hong","1234")))
                .willReturn(1);

        // then

        // 기대하는 응답 DTO 객체 생성
        ResultDto resultDto = ResultDto.builder()
                .status("ok")
                .message("로그인 성공!")
                .build();

        // 객체를 문자열로 JSON 문자열로 직렬화 한다. GSON 라이브러리 사용.
        Gson gson = new Gson();
        String content = gson.toJson(resultDto);
        System.out.println("content: "+content);

        // 또 다른 방법: ObjectMapper도 GSON과 같은 역할
        String json = new ObjectMapper().writeValueAsString(resultDto);
        System.out.println("json: "+json);

        Member member = Member.builder()
                .loginId("hong")
                .loginPw("1234")
                .build();

        mockMvc.perform(post("/api/v1/loginAction1")
                .content(gson.toJson(member))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("ok"))
                .andExpect(jsonPath("$.message").value("로그인 성공!"))
                .andExpect(content().string(content))
                .andExpect(status().isOk())
                .andDo(print());
    }
}