package com.study.Pr07LoginJoin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/")
    public String main(){
        return "redirect:/login";
    }

    // 회원가입
    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @PostMapping("/duplicate")
    @ResponseBody
    public ResDto checkDuplicate(@RequestBody Map<String, String> inputName){
        boolean check = memberService.checkDuplication(inputName.get("inputName"));

        if(inputName.get("inputName").isEmpty()){
            return new ResDto("fail-name","이름을 입력해주세요!");
        }
        if(check){
            return new ResDto("fail", "중복된 아이디가 있습니다.");
        }else{
            return new ResDto("success", "중복된 아이디가 없습니다.");
        }
    }

    @PostMapping("/join")
    public String join(RedirectAttributes rttr, JoinReqDto reqDto){
        ResDto resDto = new ResDto();

        memberService.create(reqDto);
        resDto.setStatus("success");
        resDto.setMessage("회원가입 성공!");

        rttr.addFlashAttribute("message",resDto.getMessage());
        return "redirect:/login";
    }

    // 로그인
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(LoginReqDto loginReqDto, Model model){
        ResDto resDto = new ResDto();

        if(memberService.isEmptyMembers()){
            resDto.setStatus("fail");
            resDto.setMessage("로그인 실패(회원 목록에 존재하지 않습니다.)");
        }else{
            if(memberService.isExistMember(loginReqDto)){
                resDto.setStatus("success");
                resDto.setMessage("로그인 성공");
            }else{
                resDto.setStatus("fail");
                resDto.setMessage("로그인 실패(회원 목록에 존재하지 않습니다.)");
            }
        }
        model.addAttribute("status",resDto.getStatus());
        model.addAttribute("message",resDto.getMessage());

        return "login";
    }
}
