package com.study.ProjectCompanyWeb.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public String adminLogin(){
        return "/admin/admin_login";
    }
}
