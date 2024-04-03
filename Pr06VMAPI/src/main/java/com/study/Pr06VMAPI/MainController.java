package com.study.Pr06VMAPI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(){
        return "index";
    }
    @GetMapping("/add")
    public String productAdd(){
        return "addItem";
    }

    @GetMapping("/update")
    public String productUpdate(){
        return "updateItem";
    }
}