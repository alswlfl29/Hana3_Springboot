package com.study.ProjectCompanyWeb.admin.controller;

import com.study.ProjectCompanyWeb.admin.dto.MemberResponseDto;
import com.study.ProjectCompanyWeb.admin.service.AdminService;
import com.study.ProjectCompanyWeb.member.dto.LoginRequestDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("")
    public String adminMain(HttpSession session, Model model){
        if(session.getAttribute("adminId")!=null){
            model.addAttribute("isLogin","ok");
            return "redirect:/admin/admin_member";
        }else{
            model.addAttribute("isLogin","fail");
            return "redirect:/admin/admin_login";
        }
    }

    @GetMapping("/admin_login")
    public String adminLogin(HttpSession session, Model model){
        if(session.getAttribute("adminId")!=null){
            model.addAttribute("isLogin","ok");
        }else{
            model.addAttribute("isLogin","fail");
        }
        return "/admin/admin_login";
    }

    @PostMapping("/adminLoginAction")
    @ResponseBody
    public String adminLoginAction(@ModelAttribute LoginRequestDto dto, HttpSession session){
        boolean result = adminService.existsByMemberIdAndMemberPw(dto);
        if(result){
            session.setAttribute("adminId","admin");
            return "<script>location.href='/admin';</script>;";
        }else{
            return "<script>alert('관리자가 아닙니다!');history.back();</script>;";
        }
    }

    @GetMapping("/admin_member")
    public String admin_member(HttpSession session, Model model){
        if(session.getAttribute("adminId")!=null){
            model.addAttribute("isLogin","ok");
        }else{
            model.addAttribute("isLogin","fail");
        }
        List<MemberResponseDto> members = adminService.findAllMember();
        model.addAttribute("members", members);
        return "/admin/admin_member";
    }

    @GetMapping("/admin_notice")
    public String admin_notice(HttpSession session, Model model){
        if(session.getAttribute("adminId")!=null){
            model.addAttribute("isLogin","ok");
        }else{
            model.addAttribute("isLogin","fail");
        }
        return "/admin/admin_notice";
    }

    @GetMapping("/admin_notice_view")
    public String admin_notice_view(HttpSession session, Model model){
        if(session.getAttribute("adminId")!=null){
            model.addAttribute("isLogin","ok");
        }else{
            model.addAttribute("isLogin","fail");
        }
        return "/admin/admin_notice_view";
    }

    @GetMapping("/admin_notice_write")
    public String admin_notice_write(HttpSession session, Model model){
        if(session.getAttribute("adminId")!=null){
            model.addAttribute("isLogin","ok");
        }else{
            model.addAttribute("isLogin","fail");
        }
        return "/admin/admin_notice_write";
    }
}
