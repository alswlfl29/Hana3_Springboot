package com.study.ProjectCompanyWeb.community.controller;

import com.study.ProjectCompanyWeb.community.dto.CommunityResponseDto;
import com.study.ProjectCompanyWeb.community.service.CommunityService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityService communityService;

    @GetMapping("/community01")
    public String community01(HttpSession session, Model model){
        if(session.getAttribute("userId")!=null){
            model.addAttribute("isLogin","ok");
        }else{
            model.addAttribute("isLogin","fail");
        }
        List<CommunityResponseDto> list = communityService.getCommunityList();
        model.addAttribute("list",list);
        return "/community/community01";
    }

    @PostMapping("/search")
    public String search(@RequestParam String searchKeyword, @RequestParam String searchContent, HttpSession session, Model model){
        if(session.getAttribute("userId")!=null){
            model.addAttribute("isLogin","ok");
        }else{
            model.addAttribute("isLogin","fail");
        }

        List<CommunityResponseDto> list = switch (searchKeyword) {
            case "title" -> communityService.getCommunityTitle(searchContent);
            case "content" -> communityService.getCommunityContent(searchContent);
            default -> communityService.getCommunityList();
        };
        model.addAttribute("list",list);
        return "/community/community01";
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
