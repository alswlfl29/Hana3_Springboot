package com.study.Pr01Counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    Counter counter;
    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("count",counter.getCount());
        return "index";
    }
    @GetMapping("/plus")
    public String plus(Model model){
        counter.setCount(counter.getCount()+1);
        model.addAttribute("count",counter.getCount());
        return "index";
    }
    @GetMapping("/minus")
    public String minus(Model model){
        counter.setCount(counter.getCount()-1);
        model.addAttribute("count",counter.getCount());
        return "index";
    }
}
