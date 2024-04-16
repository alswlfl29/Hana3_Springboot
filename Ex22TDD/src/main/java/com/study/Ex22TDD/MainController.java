package com.study.Ex22TDD;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(Model model){
        Member member = new Member("hong","1234");
        model.addAttribute("member", member);

        List<String> list = new ArrayList<>(List.of("hong", "lee"));
        model.addAttribute("list",list);

        return "index"; // index.html로 응답
    }
}
