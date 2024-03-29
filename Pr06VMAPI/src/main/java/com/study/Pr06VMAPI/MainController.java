package com.study.Pr06VMAPI;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Controller
public class MainController {
    public static List<Product> productList = new ArrayList<>();
    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("productList",productList);
        model.addAttribute("productLen",productList.size());
        return "index";
    }
    @GetMapping("/add")
    public String productAdd(){
        return "addItem";
    }

    @GetMapping("/update")
    public String productUpdate(@RequestParam int index, Model model){
        model.addAttribute("product",productList.get(index));
        model.addAttribute("index",index);
        return "updateItem";
    }
}