package com.study.Pr07LoginJoin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MainController {
    @GetMapping("/")
    public String main(){
        return "redirect:/login";
    }

    // 회원가입
    @GetMapping("/join")
    public String join(){
        return "join";
    }


    // 로그인
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
