package com.study.Pr01Counter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    public final Counter counter;
    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("count",counter.getCount());
        return "index";
    }
}
