package com.study.Pr02Calc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ApiController {
    @Autowired
    Calc calc;
    @GetMapping("/add")
    public int add(@RequestParam int number1, @RequestParam int number2){
        calc.add(number1,number2);
        return calc.getResult();
    }
    @GetMapping("/minus")
    public int minus(@RequestParam int number1, @RequestParam int number2){
        calc.minus(number1, number2);
        return calc.getResult();
    }
    @GetMapping("/multiply")
    public int multiply(@RequestParam int number1, @RequestParam int number2){
        calc.multiply(number1,number2);
        return calc.getResult();
    }
    @GetMapping("/division")
    public double division(@RequestParam int number1, @RequestParam int number2){
        calc.division(number1,number2);
        return calc.getDivResult();
    }
}
