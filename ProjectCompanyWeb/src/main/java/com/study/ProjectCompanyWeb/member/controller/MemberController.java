package com.study.ProjectCompanyWeb.member.controller;

import com.study.ProjectCompanyWeb.member.dto.DuplicateIdRequest;
import com.study.ProjectCompanyWeb.member.dto.JoinRequestDto;
import com.study.ProjectCompanyWeb.member.dto.LoginRequestDto;
import com.study.ProjectCompanyWeb.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public String login(HttpSession session, Model model){
        if(session.getAttribute("userId")!=null){
            return "redirect:/";
        }
        model.addAttribute("isLogin","fail");
        return "/member/login";
    }

    @GetMapping("/join")
    public String join(HttpSession session, Model model){
        if(session.getAttribute("userId")!=null){
            return "redirect:/";
        }
        model.addAttribute("isLogin","fail");
        return "/member/join2";
    }

    @PostMapping("/duplicate")
    @ResponseBody
    public boolean checkDuplicateId(@RequestBody DuplicateIdRequest dto){
        return memberService.checkDuplicateId(dto.getMemberId());
    }

    @PostMapping("/joinAction")
    @ResponseBody
    public String joinAction(@ModelAttribute JoinRequestDto dto){
        Integer newIdx = memberService.save(dto);

        boolean isFound = memberService.existsByMemberIdx(newIdx);
        if(isFound){
            return "<script>alert('회원가입되었습니다.');location.href='/member/login';</script>";
        }else{
            return "<script>alert('회원가입에 실패하였습니다.');history.back();</script>";
        }
    }

    @PostMapping("/loginAction")
    @ResponseBody
    public String loginAction(@ModelAttribute LoginRequestDto dto, HttpSession session){
        boolean check;
        String memberId;
        try{
            memberId = memberService.findByMemberId(dto.getLoginID());
            check = memberService.existsByMemberIdAndMemberPw(memberId, dto.getLoginPW());
        }catch (IllegalArgumentException e){
            return "<script>alert('"+e.getMessage()+"');history.back();</script>";
        }

        if(check){
            session.setAttribute("userId",memberId);
            return "<script>alert('로그인되었습니다.');location.href='/';</script>";
        }else{
            return "<script>alert('비밀번호가 다릅니다.');history.back();</script>";
        }
    }

    @GetMapping("/logoutAction")
    @ResponseBody
    public String logoutAction(HttpSession session){
        session.setAttribute("userId",null);
        session.invalidate();
        return "<script>alert('로그아웃 되었습니다.');location.href='/';</script>";
    }

    @GetMapping("/idFind")
    public String idFind(){
        return "/member/idFind";
    }

    @PostMapping("/idFindAction")
    @ResponseBody
    public String idFindAction(@RequestParam String userName, @RequestParam String userEmail){
        String memberId;
        try{
            memberId = memberService.findByMemberNameAndMemberEmail(userName, userEmail);
        }catch (IllegalArgumentException e){
            return "<script>alert('"+e.getMessage()+"');history.back();</script>";
        }
        return "회원님의 아이디는 "+memberId+" 입니다.\n" +
                "<div style='text-align:center; margin-top:10px;'>" +
                "<input type='image' src='../img/member/btn_close.gif' onclick='window.close()' />" +
                "</div>";
    }

    @GetMapping("/passwordFind")
    public String passwordFind(){
        return "/member/passwordFind";
    }

    @PostMapping("/passwordFindAction")
    @ResponseBody
    public String passwordFindAction(@RequestParam String userName, @RequestParam String userID, @RequestParam String userEmail){
        String memberPw;
        try{
            memberPw = memberService.findByMemberNameAndMemberIdAndMemberEmail(userName, userID, userEmail);
        }catch (IllegalArgumentException e){
            return "<script>alert('"+e.getMessage()+"');history.back();</script>";
        }
        return "회원님의 암호는 "+memberPw+" 입니다.\n" +
                "<div style='text-align:center; margin-top:10px;'>" +
                "<input type='image' src='../img/member/btn_close.gif' onclick='window.close()' />" +
                "</div>";
    }
}
