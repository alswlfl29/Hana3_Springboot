package com.study.ProjectCompanyWeb.customer.controller;

import com.study.ProjectCompanyWeb.customer.dto.QnACheckPwRequestDto;
import com.study.ProjectCompanyWeb.customer.dto.QnAResponseDto;
import com.study.ProjectCompanyWeb.customer.service.QnAService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class QnAController {
    private final QnAService qnAService;

    @GetMapping("/customer02")
    public String customer02(HttpSession session, Model model){
        if(session.getAttribute("userId")!=null){
            List<QnAResponseDto> list = qnAService.getQnAList();
            model.addAttribute("list",list);
            model.addAttribute("isLogin","ok");
            return "/customer/customer02";
        }else{
            return "/customer/requireLogin";
        }
    }

    @PostMapping("/search")
    public String search(@RequestParam String searchKeyword, @RequestParam String searchContent, HttpSession session, Model model){
        if(session.getAttribute("userId")!=null){
            model.addAttribute("isLogin","ok");
        }else{
            model.addAttribute("isLogin","fail");
        }

        List<QnAResponseDto> list = switch (searchKeyword) {
            case "title" -> qnAService.getQnATitle(searchContent);
            case "content" -> qnAService.getQnAContent(searchContent);
            case "member" -> qnAService.getQnAMember(searchContent);
            default -> qnAService.getQnAList();
        };
        model.addAttribute("list",list);
        return "/customer/customer02";
    }

    @GetMapping("/customer02_2")
    public String customer02_2(HttpSession session, Model model){
        if(session.getAttribute("userId")!=null){
            model.addAttribute("isLogin","ok");
            return "/customer/customer02_2";
        }else{
            return "/customer/requireLogin";
        }
    }

    @GetMapping("/customer02_3")
    public String customer02_3(@RequestParam Integer qna_idx, HttpSession session, Model model){
        if(session.getAttribute("userId")!=null){
            model.addAttribute("isLogin","ok");
            model.addAttribute("qna_idx", qna_idx);
            return "/customer/customer02_3";
        }else{
            return "/customer/requireLogin";
        }
    }

    @PostMapping("/qna_check_pw")
    @ResponseBody
    public String qna_check_pw(@RequestParam Integer qnaIdx, @ModelAttribute QnACheckPwRequestDto dto){
        boolean result = qnAService.existsByQnaIdxAndQnaPw(qnaIdx, dto.getQnaPw());
        if(result){
            return "<script>window.opener.location.href='/customer/customer02_4?qna_idx="+qnaIdx+"';window.close();</script>";
        }else{
            return "<script>alert('비밀번호가 일치하지않습니다.');history.back();</script>;";
        }
    }

    @GetMapping("/customer02_4")
    public String customer02_4(@RequestParam Integer qna_idx, HttpSession session, Model model){
        if(session.getAttribute("userId")!=null){
            QnAResponseDto qna = qnAService.getQnA(qna_idx);
            model.addAttribute("isLogin","ok");
            model.addAttribute("qna",qna);
            return "/customer/customer02_4";
        }else{
            return "/customer/requireLogin";
        }
    }
}
