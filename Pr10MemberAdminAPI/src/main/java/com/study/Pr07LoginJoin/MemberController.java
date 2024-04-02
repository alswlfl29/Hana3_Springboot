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
    public String list(Model model){
        List<Member> members = memberService.memberList();
        model.addAttribute("members",members);
        return "list";
    }

    // 수정화면
    @GetMapping("/update")
    public String update(@RequestParam int index, Model model ){
        Member member = memberService.getMemberData(index);
        model.addAttribute("member",member);
        model.addAttribute("index",index);
        return "update";
    }
}
