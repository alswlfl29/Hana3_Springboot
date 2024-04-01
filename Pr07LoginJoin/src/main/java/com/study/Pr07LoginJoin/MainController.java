package com.study.Pr07LoginJoin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

import static com.study.Pr07LoginJoin.MainRepository.memberList;

@Controller
@RequiredArgsConstructor
public class MainController {
    final Member member;
    private final MainService mainService;

    @GetMapping("/")
    public String main(){
        return "redirect:/login";
    }

    // 회원가입
    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @PostMapping("/dupli")
    @ResponseBody
    public ResDto checkDuplicate(@RequestBody Map<String, String> inputName){
        boolean check = member.checkDuplication(inputName.get("inputName"));

        ResDto resDto = new ResDto();
        if(inputName.get("inputName").isEmpty()){
            resDto.setStatus("fail-name");
            resDto.setMessage("이름을 입력해주세요!");
            return resDto;
        }

        if(check){
            resDto.setStatus("fail");
            resDto.setMessage( "중복된 아이디가 있습니다.");
        }else{
            resDto.setStatus("success");
            resDto.setMessage( "중복된 아이디가 없습니다.");
        }
        return resDto;
    }

    @PostMapping("/join")
    public String join(RedirectAttributes rttr, ReqDto reqDto, Model model){
        ResDto resDto = new ResDto();

        mainService.create(reqDto);
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
        if(memberList.isEmpty()){
            resDto.setStatus("fail");
            resDto.setMessage("로그인 실패(회원 목록에 존재하지 않습니다.)");
        }else{
            for(Member member:memberList){
                if(member.getUsername().equals(loginReqDto.getInputName())
                        && member.getPassword().equals(loginReqDto.getInputPw())){
                    resDto.setStatus("success");
                    resDto.setMessage("로그인 성공");
                    break;
                }else{
                    resDto.setStatus("fail");
                    resDto.setMessage("로그인 실패(회원 목록에 존재하지 않습니다.)");
                }
            }
        }
        model.addAttribute("status",resDto.getStatus());
        model.addAttribute("message",resDto.getMessage());

        return "login";
    }
}
