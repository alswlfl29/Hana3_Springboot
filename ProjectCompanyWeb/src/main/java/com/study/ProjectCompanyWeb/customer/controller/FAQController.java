package com.study.ProjectCompanyWeb.customer.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class FAQController {
    @GetMapping("/customer03")
    public String customer03(HttpSession session, Model model){
        if(session.getAttribute("userId")!=null){
            model.addAttribute("isLogin","ok");
            return "/customer/customer03";
        }else{
            return "/customer/requireLogin";
        }
    }
}
