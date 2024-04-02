package com.study.Pr07LoginJoin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberApiController {
    private final MemberService memberService;

    // 회원가입
    @PostMapping("/dupli")
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
    public ResDto join(@RequestBody JoinReqDto joinReqDto){
        this.memberService.create(joinReqDto);

        return new ResDto("success","회원가입 성공!");
    }

    // 로그인
    @PostMapping("/login")
    public ResDto login(@RequestBody LoginReqDto loginReqDto){
        if(memberService.isEmptyMembers()){
            return new ResDto("fail","로그인 실패(회원 목록에 존재하지 않습니다.)");
        }else{
            if(memberService.isExistMember(loginReqDto)){
                if(memberService.isManager(loginReqDto)){
                    return new ResDto("admin","관리자");
                }
                return new ResDto("success","로그인 성공");
            }else{
                return new ResDto("fail","로그인 실패(회원 목록에 존재하지 않습니다.)");
            }
        }
    }

    // 수정
    @PostMapping("/update")
    public ResDto update(@RequestBody UpdateReqDto updateReqDto){
        memberService.updateMember(updateReqDto);
        return new ResDto("success","수정되었습니다.");
    }

    // 삭제
    @GetMapping("/delete")
    public ResDto delete(@RequestParam int index){
        memberService.deleteMember(index);
        return new ResDto("success","삭제되었습니다.");
    }
}
