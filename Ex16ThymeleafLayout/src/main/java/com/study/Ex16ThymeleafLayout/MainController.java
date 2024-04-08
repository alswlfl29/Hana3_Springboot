package com.study.Ex16ThymeleafLayout;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(){
        return "first"; // first.html로 응답
    }
    @GetMapping("/second")
    public String second(){
        return "second"; // second.html로 응답
    }
}
