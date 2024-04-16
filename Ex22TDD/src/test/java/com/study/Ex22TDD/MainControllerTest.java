package com.study.Ex22TDD;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest: @Controller, @RestController 가 설정된 컨트롤러 클래스를 테스트한다.
//              @Service, @Repository 는 사용할 수 없음.

@WebMvcTest(MainController.class)
class MainControllerTest {
    // MockMvc: 웹 API를 테스트할 때 가상의 HTTP 요청을 만들어주는 클래스
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("main() 테스트")
    void main() throws Exception{
        Member member = new Member("hong","1234");

        mockMvc.perform(get("/"))
                .andExpect(status().isOk()) // HTTP 응답코드: 200
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("member"))
                // .andExpect(model().attribute("member",member))
                .andExpect(model().attribute("list", Matchers.contains("hong","lee")))
                .andDo(print());

    }
}