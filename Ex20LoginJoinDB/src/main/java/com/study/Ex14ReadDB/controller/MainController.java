package com.study.Ex14ReadDB.controller;

import com.study.Ex14ReadDB.dto.MemberLoginDto;
import com.study.Ex14ReadDB.dto.MemberJoinDto;
import com.study.Ex14ReadDB.entity.MemberEntity;
import com.study.Ex14ReadDB.entity.MemberRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor // 생성자 주입을 하기 위한 어노테이션
public class MainController {
    // 셍성자 주입
    final MemberRepository memberRepository;

    @GetMapping("/")
    public String main(){
        return "index";
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }

    // Valid에서 오류가 존재하면 BindingResult에 에러가 들어감
    @PostMapping("/loginAction")
    @ResponseBody
    public String loginAction(@Valid MemberLoginDto memberLoginDto, BindingResult bindingResult, HttpSession session){
        if(bindingResult.hasErrors()){
            // 바인딩 오류
            // DTO에 설정한 message 값을 가져옴
            String detail = bindingResult.getFieldError().getDefaultMessage();
            // DTO에 유효성체크를 걸어놓은 어노테이션 명을 가져온다.
            String bindResultCode = bindingResult.getFieldError().getCode();
            System.out.println(detail+": "+bindResultCode);
            return "<script>alert('"+detail+"'); history.back();</script>";
        }
        System.out.println(memberLoginDto.getUserId());
        System.out.println(memberLoginDto.getUserPw());

        // 로그인 처리 로직
        // 1. 메시지: "아이디가 없습니다"
        // 2.       "암호가 맞지 않습니다"
        /*
         * 웹 브라우저               WAS(톰캣)
         *          <--JSID------
         * 쿠키에 저장
         *          --JSID-----> 동일한 ID인지 확인(동일하면 재발급 X)
         *          --None,새로운 ID --> 재발급
         */
        Optional<MemberEntity> optional =
                memberRepository.findByUserId(memberLoginDto.getUserId());
        if(!optional.isPresent()){
            return "<script>alert('아이디가 없습니다.'); history.back();</script>";
        }

        Optional<MemberEntity> optional2 =
                memberRepository.findByUserIdAndUserPw(memberLoginDto.getUserId(),memberLoginDto.getUserPw());
        if(!optional2.isPresent()){
            return "<script>alert('암호가 맞지 않습니다.'); history.back();</script>";
        }

        // 세션에 로그인 여부/로그인 아이디/권한 저장
        session.setAttribute("isLogin", true);
        session.setAttribute("userId", optional2.get().getUserId());
        session.setAttribute("userRold", optional2.get().getUserRole());

        String userRole = optional2.get().getUserRole();
        if(userRole.equals("ROLE_ADMIN")){
            return "<script>alert('관리자 로그인 성공'); location.href='/admin';</script>";
        }else{
            return "<script>alert('로그인 성공'); location.href='/';</script>";
        }
    }

    @GetMapping("/logoutAction")
    @ResponseBody
    public String logoutAction(HttpSession session){
        // 로그아웃 액션
        session.setAttribute("isLogin", null);
        session.setAttribute("userId", null);
        session.setAttribute("userRole", null);

        session.invalidate(); // 세션종료(JSESSIONID 종료), 모든 속성 제거됨
        return "<script>alert('로그아웃 성공'); location.href='/';</script>";
    }

    // Get, Post 상관없이 모든 요청 받을 수 있음.
    @GetMapping("/admin")
    public String list(Model model){
        List<MemberEntity> list = memberRepository.findAll();
        model.addAttribute("list",list);
        model.addAttribute("listcount",memberRepository.count());
        System.out.println("size: "+list.size());
        return "admin"; // index.html로 응답
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm"; // joinForm.html로 응답
    }

    @PostMapping("/joinAction")
    @ResponseBody
    public String joinAction(@Valid @ModelAttribute MemberJoinDto dto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            // 바인딩 오류
            // DTO에 설정한 message 값을 가져옴
            String detail = bindingResult.getFieldError().getDefaultMessage();
            // DTO에 유효성체크를 걸어놓은 어노테이션 명을 가져온다.
            String bindResultCode = bindingResult.getFieldError().getCode();
            System.out.println(detail+": "+bindResultCode);
            return "<script>alert('"+detail+"'); history.back();</script>";
        }

        try {
            MemberEntity memberEntity = dto.toSaveEntity();
            memberRepository.save(memberEntity);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("회원가입 실패");
            return "<script>alert('회원가입 실패');history.back();</script>";
        }
        System.out.println("회원가입 성공");
        return "<script>alert('회원가입 성공');location.href='/';</script>";
    }

    @GetMapping("/viewMember")
    public String viewMember(@RequestParam int id, Model model){
        Optional<MemberEntity> optional =memberRepository.findById((long) id);
        if(!optional.isPresent()){
            System.out.println("회원정보가 없습니다.");
            return "redirect:/admin";
        }
        optional.ifPresent(memberEntity -> {
            model.addAttribute("member",memberEntity.toSaveDto());
            System.out.println("userName: " + memberEntity.getUserName());
        });
        return "modifyForm"; // modifyForm.html로 응답
    }

    @PostMapping("/modifyAction")
    @ResponseBody
    public String modifyAction(@ModelAttribute MemberJoinDto dto){
        try {
            MemberEntity memberEntity = dto.toUpdateEntity();
            memberRepository.save(memberEntity);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("회원정보수정 실패");
            return "<script>alert('회원정보수정 실패');history.back();</script>";
        }
        System.out.println("회원정보수정 성공");
        return "<script>alert('회원정보수정 성공!');location.href='/list';</script>";
    }

    @GetMapping("/deleteMember")
    @ResponseBody
    public String deleteMember(@RequestParam int id){
       Optional<MemberEntity> optional = memberRepository.findById((long) id);
       if(!optional.isPresent()){
           System.out.println("회원정보조회 실패");
           return "<script>alert('회원정보조회 실패');location.href='/list';</script>";
       }
       MemberEntity memberEntity = optional.get();
       try{
           memberRepository.delete(memberEntity);
       }catch (Exception e){
           e.printStackTrace();
           return "<script>alert('회원정보삭제 실패');history.back();</script>";
       }
       return "<script>alert('회원정보삭제 성공!');location.href='/list';</script>";
    }
}
