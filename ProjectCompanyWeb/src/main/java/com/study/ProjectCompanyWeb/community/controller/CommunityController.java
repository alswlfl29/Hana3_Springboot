package com.study.ProjectCompanyWeb.community.controller;

import com.study.ProjectCompanyWeb.community.dto.CommunityResponseDto;
import com.study.ProjectCompanyWeb.community.service.CommunityService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
        List<CommunityResponseDto> communities = communityService.findAllCommunity();
        model.addAttribute("list", communities);
        return "/community/community01";
    }

    @GetMapping(value = "/community01", params = {"search_select", "search_keyword"})
    public String community01(HttpSession session,
                              @RequestParam("search_select") String search_select, @RequestParam("search_keyword") String search_keyword, Model model){
        if(session.getAttribute("userId")!=null){
            model.addAttribute("isLogin","ok");
        }else{
            model.addAttribute("isLogin","fail");
        }

        List<CommunityResponseDto> communities = (List<CommunityResponseDto>) model.getAttribute("communities");
        if (communities == null || communities.isEmpty()) {
            search_select = URLEncoder.encode(search_select, StandardCharsets.UTF_8);
            search_keyword = URLEncoder.encode(search_keyword, StandardCharsets.UTF_8);
            return "redirect:/community/search?searchKeyword=" + search_select + "&searchContent=" + search_keyword;
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
        redirectAttributes.addAttribute("search_select",searchKeyword);
        redirectAttributes.addAttribute("search_keyword",searchContent);
        redirectAttributes.addFlashAttribute("communities",list);
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
