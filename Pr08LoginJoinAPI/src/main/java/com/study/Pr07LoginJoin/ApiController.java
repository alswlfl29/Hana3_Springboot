package com.study.Pr07LoginJoin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ApiController {
    private final MemberService memberService;
    @PostMapping("/duplicate")
    @ResponseBody
    public ResDto checkDuplicate(@RequestBody Map<String, String> inputName){
        boolean check = memberService.checkDuplication(inputName.get("inputName"));

        if(check){
            return new ResDto("fail", "중복된 아이디가 있습니다.");
        }else{
            return new ResDto("success", "중복된 아이디가 없습니다.");
        }

    }

    @PostMapping("/join")
    public ResDto join(@RequestBody JoinReqDto joinReqDto){
        memberService.create(joinReqDto);
        return new ResDto("success","회원가입 성공!");
    }

    @PostMapping("/login")
    public ResDto login(@RequestBody LoginReqDto loginReqDto){
        if(memberService.isEmpty()){
            return new ResDto("fail","로그인 실패(회원 목록에 존재하지 않습니다.)");
        }
        if(memberService.checkIsMember(loginReqDto)){
            return new ResDto("success","로그인 성공");
        }else{
            return new ResDto("fail","로그인 실패(회원 목록에 존재하지 않습니다.)");
        }
    }
}
