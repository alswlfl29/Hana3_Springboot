package com.study.ProjectCompanyWeb.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(HttpSession session, Model model){
        if(session.getAttribute("userId")!=null){
            model.addAttribute("isLogin","ok");
        }else{
            model.addAttribute("isLogin","fail");
        }
        return "index";
    }

    @GetMapping("/company/company01")
    public String company01(HttpSession session, Model model){
        if(session.getAttribute("userId")!=null){
            model.addAttribute("isLogin","ok");
        }else{
            model.addAttribute("isLogin","fail");
        }
        return "/company/company01";
    }

    @GetMapping("/company/company03")
    public String company03(HttpSession session, Model model){
        if(session.getAttribute("userId")!=null){
            model.addAttribute("isLogin","ok");
        }else{
            model.addAttribute("isLogin","fail");
        }
        return "/company/company03";
    }

    @GetMapping("/product/product01")
    public String product01(HttpSession session, Model model){
        if(session.getAttribute("userId")!=null){
            model.addAttribute("isLogin","ok");
        }else{
            model.addAttribute("isLogin","fail");
        }
        return "/product/product01";
    }

    @GetMapping("/business/business01")
    public String business01(HttpSession session, Model model){
        if(session.getAttribute("userId")!=null){
            model.addAttribute("isLogin","ok");
        }else{
            model.addAttribute("isLogin","fail");
        }
        return "/business/business01";
    }


    @GetMapping("/idFind")
    public String idFind(){
        return "/member/idFind";
    }
}
