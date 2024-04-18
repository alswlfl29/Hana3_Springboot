package com.study.ProjectCompanyWeb.customer.controller;

import com.study.ProjectCompanyWeb.customer.dto.One2OneRequestDto;
import com.study.ProjectCompanyWeb.customer.service.One2OneService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class One2OneController {
    private final One2OneService one2OneService;

    @GetMapping("/customer01")
    public String customer01(HttpSession session, Model model){
        if(session.getAttribute("userId")!=null){
            model.addAttribute("isLogin","ok");
            return "/customer/customer01";
        }else{
            return "/customer/requireLogin";
        }
    }

    @PostMapping("/one2oneAction")
    @ResponseBody
    public String one2oneAction(@ModelAttribute One2OneRequestDto dto){
        Integer newIdx = one2OneService.save(dto);

        boolean isFound = one2OneService.existsByOne2OneIdx(newIdx);
        if(isFound){
            return "<script>alert('1:1문의가 등록 되었습니다.');location.href='/';</script>";
        }else{
            return "<script>alert('1:1문의가 등록을 실패하였습니다.');history.back();</script>";
        }
    }
}
