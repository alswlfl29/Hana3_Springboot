package com.study.ProjectCompanyWeb.customer.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @GetMapping("/customer01")
    @ResponseBody
    public String customer01(HttpSession session, Model model){
        if(session.getAttribute("userId")!=null){
            model.addAttribute("isLogin","ok");
            return "<script>location.href='/customer/customer01Page';</script>";
        }else{
            return "<script>alert('로그인을 해주세요.');location.href='/member/login';</script>";
        }
    }

    @GetMapping("/customer01Page")
    public String customer01Page(Model model){
        model.addAttribute("isLogin","ok");
        return "/customer/customer01";
    }

    @GetMapping("/customer02")
    @ResponseBody
    public String customer02(HttpSession session, Model model){
        if(session.getAttribute("userId")!=null){
            return "<script>location.href='/customer/customer02Page';</script>";
        }else{
            return "<script>alert('로그인을 해주세요.');location.href='/member/login';</script>";
        }
    }

    @GetMapping("/customer02Page")
    public String customer02Page(Model model){
        model.addAttribute("isLogin","ok");
        return "/customer/customer02";
    }

    @GetMapping("/customer03")
    @ResponseBody
    public String customer03(HttpSession session, Model model){
        if(session.getAttribute("userId")!=null){
            model.addAttribute("isLogin","ok");
            return "<script>location.href='/customer/customer03Page';</script>";
        }else{
            return "<script>alert('로그인을 해주세요.');location.href='/member/login';</script>";
        }
    }

    @GetMapping("/customer03Page")
    public String customer03Page(Model model){
        model.addAttribute("isLogin","ok");
        return "/customer/customer03";
    }
}
