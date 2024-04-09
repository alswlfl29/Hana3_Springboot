package com.study.Ex14ReadDB.controller;

import com.study.Ex14ReadDB.dao.IMemberDao;
import com.study.Ex14ReadDB.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor // 생성자 주입을 하기 위한 어노테이션
public class MainController {
    // 셍성자 주입
    final IMemberDao memberDao;

    @GetMapping("/")
    public String main(){
        return "redirect:/list";
    }

    // Get, Post 상관없이 모든 요청 받을 수 있음.
    @RequestMapping("/list")
    public String list(Model model){
        List<MemberDto> list = memberDao.list();
        model.addAttribute("list",list);
        model.addAttribute("listcount",memberDao.count());
        return "index"; // index.html로 응답
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm"; // joinForm.html로 응답
    }

    @PostMapping("/joinAction")
    @ResponseBody
    public String joinAction(@ModelAttribute MemberDto dto){
        System.out.println("name"+dto.getUserName());
        try {
            int result = memberDao.insert(dto);
            System.out.println("result: "+result);
            if(result != 1) {
                return "<script>alert('회원가입 실패');history.back();</script>";
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("회원가입 실패");
            return "<script>alert('회원가입 실패');history.back();</script>";
        }
        System.out.println("회원가입 성공");
        return "<script>alert('회원가입 성공');location.href='/list';</script>";
    }

    @GetMapping("/viewMember")
    public String viewMember(@RequestParam int id, Model model){
        Optional<MemberDto> dto = memberDao.findById(id);
        if(dto.isPresent()){
            model.addAttribute("member", dto.get());
        }else{
            System.out.println("회원정보가 없습니다.");
            return "redirect:/list";
        }
        System.out.println("userName: " + dto.get().getUserName());
        return "modifyForm"; // modifyForm.html로 응답
    }

    @PostMapping("/modifyAction")
    @ResponseBody
    public String modifyAction(@ModelAttribute MemberDto dto){
        try {
            int result = memberDao.update(dto);
            if(result != 1){
                System.out.println("회원정보수정 실패");
                return "<script>alert('회원정보수정 실패');history.back();</script>";
            }
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
       try{
           int result = memberDao.delete(id);
           // int result = memberDao.deleteMap(1,"hong3");
           if(result != 1){
               return "<script>alert('회원정보삭제 실패');history.back();</script>";
           }
       }catch (Exception e){
           e.printStackTrace();
           return "<script>alert('회원정보삭제 실패');history.back();</script>";
       }
       return "<script>alert('회원정보삭제 성공!');location.href='/list';</script>";
    }
}
