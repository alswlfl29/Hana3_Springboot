package com.study.Pr03VM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @PostMapping("/add")
    public String productAdd(@RequestParam String pName, @RequestParam int pPrice, @RequestParam LocalDate pExpDate){
        Product product = new Product();
        product.setName(pName);
        product.setPrice(pPrice);
        product.setLimit_date(pExpDate);

        productList.add(product);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String productUpdate(@RequestParam int index, Model model){
        model.addAttribute("product",productList.get(index));
        model.addAttribute("index",index);
        return "updateItem";
    }

    @PostMapping("/update")
    public String updateAction(@RequestParam int index, @RequestParam String pName, @RequestParam int pPrice, @RequestParam LocalDate pExpDate, Model model){
        Product product = new Product();
        product.setName(pName);
        product.setPrice(pPrice);
        product.setLimit_date(pExpDate);

        productList.set(index, product);
        return "redirect:/";
    }

    @GetMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam int index, Model model){
        productList.remove(index);
        return "<script>alert('삭제되었습니다.');location.href='/';</script>";
    }
}
