package com.study.springboot.controller;

import com.study.springboot.dto.MemberJoinDto;
import com.study.springboot.entity.MemberEntity;
import com.study.springboot.entity.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm"; // loginForm.html 응답
    }
    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm";
    }
    @PostMapping("/joinAction")
    @ResponseBody
    public String joinAction(@Valid @ModelAttribute MemberJoinDto dto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            String detail = bindingResult.getFieldError().getDefaultMessage();
            String bindResultCode = bindingResult.getFieldError().getCode();
            System.out.println(detail+":"+bindResultCode);
            return "<script>alert('"+detail+"'); history.back();</script>";
        }

        // 비번은 암호화해서 저장해야함
        String encodePassword = passwordEncoder.encode(dto.getPassword());
        System.out.println("encodePassword: "+encodePassword);
        dto.setPassword(encodePassword);

        // 회원가입 DB 액션 수행
        try{
            MemberEntity entity = dto.toSaveEntity();
            memberRepository.save(entity);
        }catch (DataIntegrityViolationException e){
            // 중복된 아이디인 경우
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "<script>alert('이미 등록된 사용자입니다.'); history.back();</script>";
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return "<script>alert('회원가입 실패했습니다.'); history.back();</script>";
        }

        // HTTP 응답 코드: 200 성공
        HttpStatus status = HttpStatus.OK;
        if(status == HttpStatus.OK){
            System.out.println("회원가입 성공!");
            return "<script>alert('회원가입 성공!'); location.href='/loginForm';</script>";
        }else{
            System.out.println("회원가입 실패!");
            return "<script>alert('회원가입 실패하였습니다'); history.back();</script>";
        }
    }

    @GetMapping("/admin")
    public String admin(Model model){
        long listCount = memberRepository.count();
        model.addAttribute("listCount", listCount);

        List<MemberEntity> list = memberRepository.findAll();
        model.addAttribute("list", list);

        return "admin";
    }

    @RequestMapping("/viewDTO")
    public String viewDTO(@RequestParam("id") int id,
                          Model model) throws Exception {
        Optional<MemberEntity> optional = memberRepository.findById(Long.valueOf(id));
        if( !optional.isPresent() ) {
            throw new Exception("member id is wrong!");
        }
        MemberEntity entity = optional.get();
        model.addAttribute("member", entity);

        return "modifyForm";
    }
    @RequestMapping("/modifyAction")
    @ResponseBody
    public String modifyAction(@ModelAttribute MemberJoinDto memberJoinDto) {

        try{
            MemberEntity entity = memberJoinDto.toUpdateEntity();
            memberRepository.save( entity );
        }
        catch ( IllegalArgumentException e ){
            e.printStackTrace();
            return "<script>alert('회원정보 수정 실패'); history.back();</script>";
        }

        return "<script>alert('회원정보 수정 성공!'); location.href='/viewDTO?id=" + memberJoinDto.getId() + "';</script>";
    }
    @RequestMapping("/deleteDTO")
    @ResponseBody
    public String deleteDTO(@RequestParam("id") int id) throws Exception {

        Optional<MemberEntity> optional = memberRepository.findById(Long.valueOf(id));
        if( !optional.isPresent() ) {
            throw new Exception("member id is wrong!");
        }
        MemberEntity entity = optional.get();

        try{
            memberRepository.delete( entity );
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
            return "<script>alert('회원정보 삭제 실패'); history.back();</script>";
        }
        return "<script>alert('회원정보 삭제 성공'); location.href='/admin';</script>";
    }
}
