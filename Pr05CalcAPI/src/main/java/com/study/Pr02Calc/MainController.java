package com.study.Pr02Calc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    Calc calc;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("number1", calc.getNumber1());
        model.addAttribute("number2", calc.getNumber2());
        model.addAttribute("result", calc.getResult());
        return "index";
    }
}
