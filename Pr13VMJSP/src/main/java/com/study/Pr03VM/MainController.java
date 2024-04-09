package com.study.Pr03VM;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
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

    // Form의 Input을 받는 방법
    // 1. @RequestParam 단일 매개변수
    // 2. @ModelAttribute 객체 매핑(바인딩)
    @PostMapping("/add")
    @ResponseBody
    public String productAdd(
            // null 을 허용한다.
            // @RequestParam(required = false, defaultValue = "기본값") String pName,
            @RequestParam String pName,
            @RequestParam int pPrice,
            @RequestParam LocalDate pExpDate){
        Product product = Product.builder()
                .name(pName)
                .price(pPrice)
                .limit_date(pExpDate)
                .build();

        productList.add(product);
        // return "redirect:/";
        // 웹브라우저에게 응답을 주면서, 리다이렉트할 경로를 전달하면, 웹브라우저는 받자마자 이 경로로 다시 요청함.
        return "<script>alert('추가되었습니다.');location.href='/';</script>";
    }

    @GetMapping("/update")
    public String productUpdate(@RequestParam int index, Model model){
        Product product = productList.get(index);
        model.addAttribute("product",product);
        model.addAttribute("index",index);
        return "updateItem";
    }

    @PostMapping("/update")
    @ResponseBody
    public String updateAction(@RequestParam int index, @RequestParam String pName, @RequestParam int pPrice, @RequestParam LocalDate pExpDate){
        Product product = productList.get(index);
        product.setName(pName);
        product.setPrice(pPrice);
        product.setLimit_date(pExpDate);

        productList.set(index, product);
        return "<script>alert('수정되었습니다.');location.href='/';</script>";
    }

    @GetMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam int index){
        productList.remove(index);
        return "<script>alert('삭제되었습니다.');location.href='/';</script>";
    }
}
