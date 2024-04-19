package com.study.ProjectCompanyWeb.customer.controller;

import com.study.ProjectCompanyWeb.community.dto.CommunityResponseDto;
import com.study.ProjectCompanyWeb.customer.dto.QnACheckPwRequestDto;
import com.study.ProjectCompanyWeb.customer.dto.QnAResponseDto;
import com.study.ProjectCompanyWeb.customer.service.QnAService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class QnAController {
    private final QnAService qnAService;

    @GetMapping("/customer02")
    public String customer02(HttpSession session, @ModelAttribute("qnaList") List<QnAResponseDto> qnaList,
                             @ModelAttribute("search_select") String search_select, @ModelAttribute("search_keyword") String search_keyword, Model model){
        if(session.getAttribute("userId")!=null){
            model.addAttribute("list", qnaList);
            model.addAttribute("isLogin","ok");
            model.addAttribute("search_select", search_select);
            model.addAttribute("search_keyword", search_keyword);
            return "/customer/customer02";
        }else{
            return "/customer/requireLogin";
        }
    }

    @GetMapping("/search")
    public String search(@RequestParam String searchKeyword, @RequestParam String searchContent, RedirectAttributes redirectAttributes){
        List<QnAResponseDto> list = switch (searchKeyword) {
            case "title" -> qnAService.findAllQnAByQnATitle(searchContent);
            case "content" -> qnAService.findAllQnAByQnAContent(searchContent);
            case "member" -> qnAService.findAllQnAByQnAName(searchContent);
            default -> qnAService.findAllQnA();
        };
        redirectAttributes.addFlashAttribute("qnaList",list);
        redirectAttributes.addFlashAttribute("search_select",searchKeyword);
        redirectAttributes.addFlashAttribute("search_keyword",searchContent);
        return "redirect:/customer/customer02";
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
