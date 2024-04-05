package com.study.Pr06VMAPI.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(){
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(){
        return "index";
    }
    @GetMapping("/addForm")
    public String productAdd(){
        return "addItem";
    }

    @GetMapping("/update")
    public String productUpdate(){
        return "updateItem";
    }
}