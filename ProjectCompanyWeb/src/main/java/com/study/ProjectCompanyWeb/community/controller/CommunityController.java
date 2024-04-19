package com.study.ProjectCompanyWeb.community.controller;

import com.study.ProjectCompanyWeb.community.dto.CommunityResponseDto;
import com.study.ProjectCompanyWeb.community.service.CommunityService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityService communityService;

    @GetMapping("/community01")
    public String community01(HttpSession session, @ModelAttribute("communities") List<CommunityResponseDto> communities,
                              @ModelAttribute("search_select") String search_select, @ModelAttribute("search_keyword") String search_keyword, Model model){
        if(session.getAttribute("userId")!=null){
            model.addAttribute("isLogin","ok");
        }else{
            model.addAttribute("isLogin","fail");
        }
        model.addAttribute("list", communities);
        model.addAttribute("search_select", search_select);
        model.addAttribute("search_keyword", search_keyword);
        return "/community/community01";
    }

    @GetMapping("/search")
    public String search(@RequestParam String searchKeyword, @RequestParam String searchContent, RedirectAttributes redirectAttributes){
        List<CommunityResponseDto> list = switch (searchKeyword) {
            case "title" -> communityService.findAllCommunityByNoticeTitle(searchContent);
            case "content" -> communityService.findAllCommunityByNoticeContent(searchContent);
            default -> communityService.findAllCommunity();
        };
        redirectAttributes.addFlashAttribute("communities",list);
        redirectAttributes.addFlashAttribute("search_select",searchKeyword);
        redirectAttributes.addFlashAttribute("search_keyword",searchContent);
        return "redirect:/community/community01";
    }

    @GetMapping("community01_1")
    public String community01_1(@RequestParam Integer notice_idx, HttpSession session, Model model){
        if(session.getAttribute("userId")!=null){
            model.addAttribute("isLogin","ok");
        }else{
            model.addAttribute("isLogin","fail");
        }

        try {
            CommunityResponseDto community = communityService.getCommunityItem(notice_idx);
            model.addAttribute("community",community);
        }catch (IllegalArgumentException e){
            return "<script>alert('"+e.getMessage()+")';history.back();</script>";
        }
        return "/community/community01_1";
    }
}
