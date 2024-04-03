package com.study.Pr07LoginJoin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;

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

    // 관리자화면
    @GetMapping("/list")
    public String list(){
        return "list";
    }

    // 수정화면
    @GetMapping("/update")
    public String update(){
        return "update";
    }
}
