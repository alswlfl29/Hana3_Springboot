package com.study.ProjectCompanyWeb.admin.controller;

import com.study.ProjectCompanyWeb.admin.dto.CommunitySaveRequestDto;
import com.study.ProjectCompanyWeb.admin.dto.CommunityUpdateRequestDto;
import com.study.ProjectCompanyWeb.admin.dto.MemberResponseDto;
import com.study.ProjectCompanyWeb.admin.dto.MemberSearchRequestDto;
import com.study.ProjectCompanyWeb.admin.service.AdminService;
import com.study.ProjectCompanyWeb.community.domain.Community;
import com.study.ProjectCompanyWeb.community.dto.CommunityResponseDto;
import com.study.ProjectCompanyWeb.member.dto.LoginRequestDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

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
            return "redirect:/admin/admin_login";
        }
        List<MemberResponseDto> members =  adminService.findAllMember();
        model.addAttribute("members", members);
        model.addAttribute("memberSize", members.size());
        return "/admin/admin_member";
    }

    @GetMapping(value = "/admin_member", params = {"search_select", "search_keyword"})
    public String admin_member_search(HttpSession session,
                               @RequestParam("search_select") String search_select, @RequestParam("search_keyword") String search_keyword,
                               @ModelAttribute("order_select") String order_select, @ModelAttribute("page_select") String page_select, Model model)
                                throws IllegalStateException{
        if(session.getAttribute("adminId")!=null){
            model.addAttribute("isLogin","ok");
        }else{
            model.addAttribute("isLogin","fail");
            return "redirect:/admin/admin_login";
        }

        List<MemberResponseDto> members = (List<MemberResponseDto>) model.getAttribute("members");
        if (members == null || members.isEmpty()) {
            search_select = URLEncoder.encode(search_select, StandardCharsets.UTF_8);
            search_keyword = URLEncoder.encode(search_keyword, StandardCharsets.UTF_8);
            return "redirect:/admin/admin_search_member?search_select=" + search_select + "&search_keyword=" + search_keyword;
        }

        model.addAttribute("members", members);
        model.addAttribute("memberSize", members.size());
        model.addAttribute("search_select", search_select);
        model.addAttribute("search_keyword", search_keyword);
        model.addAttribute("order_select", order_select);
        model.addAttribute("page_select", page_select);
        return "/admin/admin_member";
    }

    @GetMapping("/admin_search_member")
    public String admin_search_member(@RequestParam("search_select") String search_select, @RequestParam("search_keyword") String search_keyword, RedirectAttributes redirectAttributes){
        List<MemberResponseDto> list = switch (search_select) {
            case "all" -> adminService.findAllMemberByNone(search_keyword);
            case "id" -> adminService.findAllMemberByMemberId(search_keyword);
            case "name" -> adminService.findAllMemberByMemberName(search_keyword);
            case "email" -> adminService.findAllMemberByMemberEmail(search_keyword);
            default -> adminService.findAllMember();
        };
        System.out.println("진입");
        redirectAttributes.addFlashAttribute("members",list);
        redirectAttributes.addAttribute("search_select",search_select);
        redirectAttributes.addAttribute("search_keyword",search_keyword);
        redirectAttributes.addFlashAttribute("order_select","memberId_ASC");
        redirectAttributes.addFlashAttribute("page_select","all");
        return "redirect:/admin/admin_member";
    }

    @PostMapping("/admin_member_list_change")
    public String admin_member_list_change(@ModelAttribute MemberSearchRequestDto dto, RedirectAttributes redirectAttributes){
        List<MemberResponseDto> list = switch (dto.getSearch_select()) {
            case "all" -> adminService.findAllMemberByNoneOrderBy(dto.getSearch_keyword(), dto.getOrder_select(), dto.getPage_select());
            case "id" -> adminService.findAllMemberByMemberIdOrderBy(dto.getSearch_keyword(), dto.getOrder_select(), dto.getPage_select());
            case "name" -> adminService.findAllMemberByMemberNameOrderBy(dto.getSearch_keyword(), dto.getOrder_select(), dto.getPage_select());
            case "email" -> adminService.findAllMemberByMemberEmailOrderBy(dto.getSearch_keyword(), dto.getOrder_select(), dto.getPage_select());
            default -> adminService.findAllMemberOrderBy(dto.getOrder_select(), dto.getPage_select());
        };

        redirectAttributes.addFlashAttribute("members",list);
        redirectAttributes.addAttribute("search_select", dto.getSearch_select());
        redirectAttributes.addAttribute("search_keyword", dto.getSearch_keyword());
        redirectAttributes.addFlashAttribute("order_select", dto.getOrder_select());
        redirectAttributes.addFlashAttribute("page_select", dto.getPage_select());
        return "redirect:/admin/admin_member";
    }

    @GetMapping("/admin_notice")
    public String admin_notice(HttpSession session, Model model){
        if(session.getAttribute("adminId")!=null){
            model.addAttribute("isLogin","ok");
        }else{
            model.addAttribute("isLogin","fail");
            return "redirect:/admin/admin_login";
        }
        List<CommunityResponseDto> communities = adminService.findAllCommunity();
        model.addAttribute("communityList", communities);
        model.addAttribute("communitySize", communities.size());
        return "/admin/admin_notice";
    }

    @GetMapping(value = "/admin_notice", params = {"search_select", "search_keyword"})
    public String admin_notice_search(HttpSession session,
                               @RequestParam("search_select") String search_select, @RequestParam("search_keyword") String search_keyword,
                               @ModelAttribute("order_select") String order_select, @ModelAttribute("page_select") String page_select, Model model){
        if(session.getAttribute("adminId")!=null){
            model.addAttribute("isLogin","ok");
        }else{
            model.addAttribute("isLogin","fail");
            return "redirect:/admin/admin_login";
        }

        List<CommunityResponseDto> communities = (List<CommunityResponseDto>) model.getAttribute("communities");
        if (communities == null || communities.isEmpty()) {
            search_select = URLEncoder.encode(search_select, StandardCharsets.UTF_8);
            search_keyword = URLEncoder.encode(search_keyword, StandardCharsets.UTF_8);
            return "redirect:/admin/admin_search_community?search_select=" + search_select + "&search_keyword=" + search_keyword;
        }

        model.addAttribute("communityList", communities);
        model.addAttribute("communitySize", communities.size());
        model.addAttribute("search_select", search_select);
        model.addAttribute("search_keyword", search_keyword);
        model.addAttribute("order_select", order_select);
        model.addAttribute("page_select", page_select);
        return "/admin/admin_notice";
    }

    @GetMapping("/admin_search_community")
    public String admin_search_community(@RequestParam("search_select") String search_select, @RequestParam("search_keyword") String search_keyword, RedirectAttributes redirectAttributes){
        List<CommunityResponseDto> list = switch (search_select) {
            case "all" -> adminService.findAllCommunityByNone(search_keyword);
            case "title" -> adminService.findAllCommunityByNoticeTitle(search_keyword);
            case "content" -> adminService.findAllCommunityByNoticeContent(search_keyword);
            case "id" -> adminService.findAllCommunityByNoticeMemberId(search_keyword);
            default -> adminService.findAllCommunity();
        };
        redirectAttributes.addFlashAttribute("communities",list);
        redirectAttributes.addAttribute("search_select",search_select);
        redirectAttributes.addAttribute("search_keyword",search_keyword);
        redirectAttributes.addFlashAttribute("order_select","noticeIdx_ASC");
        redirectAttributes.addFlashAttribute("page_select","all");
        return "redirect:/admin/admin_notice";
    }

    @PostMapping("/admin_community_list_change")
    public String admin_community_list_change(@ModelAttribute MemberSearchRequestDto dto, RedirectAttributes redirectAttributes){
        List<CommunityResponseDto> list = switch (dto.getSearch_select()) {
            case "all" -> adminService.findAllCommunityByNoneOrderBy(dto.getSearch_keyword(), dto.getOrder_select(), dto.getPage_select());
            case "title" -> adminService.findAllCommunityByNoticeTitleOrderBy(dto.getSearch_keyword(), dto.getOrder_select(), dto.getPage_select());
            case "content" -> adminService.findAllCommunityByNoticeContentOrderBy(dto.getSearch_keyword(), dto.getOrder_select(), dto.getPage_select());
            case "id" -> adminService.findAllCommunityByNoticeMemberIdOrderBy(dto.getSearch_keyword(), dto.getOrder_select(), dto.getPage_select());
            default -> adminService.findAllCommunityOrderBy(dto.getOrder_select(), dto.getPage_select());
        };

        redirectAttributes.addFlashAttribute("communities",list);
        redirectAttributes.addAttribute("search_select", dto.getSearch_select());
        redirectAttributes.addAttribute("search_keyword", dto.getSearch_keyword());
        redirectAttributes.addFlashAttribute("order_select", dto.getOrder_select());
        redirectAttributes.addFlashAttribute("page_select", dto.getPage_select());
        return "redirect:/admin/admin_notice";
    }

    @GetMapping("/admin_notice_view")
    public String admin_notice_view(@RequestParam Integer notice_idx, @RequestParam Integer notice_count, HttpSession session, Model model){
        if(session.getAttribute("adminId")!=null){
            CommunityResponseDto community = adminService.getCommunity(notice_idx);
            model.addAttribute("isLogin","ok");
            model.addAttribute("community", community);
            model.addAttribute("notice_count",notice_count);
        }else{
            model.addAttribute("isLogin","fail");
            return "redirect:/admin/admin_login";
        }
        return "/admin/admin_notice_view";
    }

    @GetMapping("/admin_notice_write")
    public String admin_notice_write(HttpSession session, Model model){
        if(session.getAttribute("adminId")!=null){
            model.addAttribute("isLogin","ok");
        }else{
            model.addAttribute("isLogin","fail");
            return "redirect:/admin/admin_login";
        }
        return "/admin/admin_notice_write";
    }

    @PostMapping("/admin_notice_write")
    @ResponseBody
    public String admin_notice_write(@ModelAttribute CommunitySaveRequestDto dto, HttpSession session){
        dto.setNoticeMemberId((String) session.getAttribute("adminId"));
        Integer newIdx = adminService.communitySave(dto);
        boolean find = adminService.existsByNoticeIdx(newIdx);
        if(find){
            return "<script>alert('공지사항을 추가하였습니다');location.href='/admin/admin_notice';</script>;";
        }else{
            return "<script>alert('공지사항 추가를 실패하였습니다');history.back();</script>;";
        }
    }

    @PostMapping("/admin_notice_modify")
    @ResponseBody
    public String admin_notice_modify(@RequestParam Integer notice_idx, @RequestParam Integer notice_count ,@ModelAttribute CommunityUpdateRequestDto dto){
        Community community = adminService.communityUpdate(notice_idx, dto);
        if(Objects.equals(community.getNoticeIdx(), notice_idx)){
            return "<script>alert('공지사항이 수정되었습니다.'); location.href='/admin/admin_notice_view?notice_idx="+notice_idx+"&notice_count="+notice_count+"';</script>";
        }else{
            return "<script>alert('공지사항 수정을 실패하였습니다.'); history.back();</script>";
        }
    }

    // @GetMapping("/admin_notice_delete")
    // @ResponseBody
    // public String admin_notice_delete(@RequestParam Integer notice_idx){
    //     try{
    //         adminService.deleteNotice(notice_idx);
    //         boolean find = adminService.existsByNoticeIdx(notice_idx);
    //         if(!find){
    //             return "<script>alert('공지사항을 삭제하였습니다');location.href='/admin/admin_notice';</script>;";
    //         }else{
    //             return "<script>alert('공지사항 삭제를 실패하였습니다');history.back();</script>;";
    //         }
    //     }catch (Exception e){
    //         return "<script>alert('공지사항 삭제를 실패하였습니다');history.back();</script>;";
    //     }
    // }
}
