package com.example.Ex03Autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    // 호출 URL: localhost:8080
    @GetMapping("/")
    @ResponseBody
    public String main(){
        return "스프링부트 웹 어플립니다.";
    }

    // 필드 주입
    @Autowired
    private Member member;

    // 호출 URL: localhost:8080/member
    @GetMapping("/member")
    @ResponseBody
    public String member(){
        member.setName("홍길동");
        return "member() 호출됨:"+member.getName();
    }

    @Autowired
    @Qualifier("cardBC") // 주입될 객체를 직접 지정함.
    ICard iCard; // 인터페이스 구현 객체를 주입해줌.
                 // BCCard, KakaoCard 중 어떤거를 주입할까?

    @GetMapping("/card")
    @ResponseBody
    public String card(){
        member.setiCard(iCard);
        member.getiCard().buy("휴대폰");
        return "card() 호출됨.";
    }
}
