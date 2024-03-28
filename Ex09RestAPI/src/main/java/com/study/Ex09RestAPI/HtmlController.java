package com.study.Ex09RestAPI;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class HtmlController {
    @GetMapping("/")
    public String main(){
        // 첫 화면 진입시 데이터 초기화한다.
        Member member = new Member("hong", "1234","abc@dot.com", LocalDate.now());
        // @Builder 기능(위와 동일함)
        // 장점: 내가 무슨 매개변수를 넣어야하는지 알 수 있음(.을 치면), 생성자함수처럼 모든 값을 안넣어도됨(선택적으로 넣을 수 있음)
        Member member1 = Member.builder()
                .username("hong")
                .password("1234")
                .email("abc@dot.com")
                .joinData(LocalDate.now())
                .build();
        ApiController.memberList.add(member);
        return "login"; // login.html로 응답
    }
}
