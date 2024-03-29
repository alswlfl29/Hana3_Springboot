package com.study.Pr02Calc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {
    final Calc calc;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("result", calc.getResult());
        return "index";
    }

    @GetMapping("/add")
    public String add(@RequestParam int number1, @RequestParam int number2, Model model){
        int result = calc.add(number1,number2);
        model.addAttribute("number1",number1);
        model.addAttribute("number2", number2);
        model.addAttribute("result", result);
        return "index";
    }
    @GetMapping("/minus")
    public String minus(@RequestParam int number1, @RequestParam int number2, Model model){
        int result = calc.minus(number1, number2);
        model.addAttribute("number1",number1);
        model.addAttribute("number2", number2);
        model.addAttribute("result", result);
        return "index";
    }
    @GetMapping("/multiply")
    public String multiply(@RequestParam int number1, @RequestParam int number2, Model model){
        int result = calc.multiply(number1, number2);
        model.addAttribute("number1",number1);
        model.addAttribute("number2", number2);
        model.addAttribute("result", result);
        return "index";
    }
    @GetMapping("/division")
    public String division(@RequestParam int number1, @RequestParam int number2, Model model){
        double result = calc.division(number1, number2);
        model.addAttribute("number1",number1);
        model.addAttribute("number2", number2);
        model.addAttribute("result", result);
        return "index";
    }

}
