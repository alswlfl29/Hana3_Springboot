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

    @GetMapping("/add")
    public String add(@RequestParam int number1, @RequestParam int number2, Model model){
        calc.add(number1,number2);
        model.addAttribute("number1",calc.getNumber1());
        model.addAttribute("number2",calc.getNumber2());
        model.addAttribute("result",calc.getResult());
        return "index";
    }
    @GetMapping("/minus")
    public String minus(@RequestParam int number1, @RequestParam int number2, Model model){
        calc.minus(number1, number2);
        model.addAttribute("number1",calc.getNumber1());
        model.addAttribute("number2",calc.getNumber2());
        model.addAttribute("result",calc.getResult());
        return "index";
    }
    @GetMapping("/multiply")
    public String multiply(@RequestParam int number1, @RequestParam int number2, Model model){
        calc.multiply(number1, number2);
        model.addAttribute("number1",calc.getNumber1());
        model.addAttribute("number2",calc.getNumber2());
        model.addAttribute("result",calc.getResult());
        return "index";
    }
    @GetMapping("/division")
    public String division(@RequestParam int number1, @RequestParam int number2, Model model){
        calc.division(number1, number2);
        model.addAttribute("number1",calc.getNumber1());
        model.addAttribute("number2",calc.getNumber2());
        model.addAttribute("result",calc.getDivResult());
        return "index";
    }

}
