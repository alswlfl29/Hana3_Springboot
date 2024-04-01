package com.study.Pr07LoginJoin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.study.Pr07LoginJoin.MainRepository.memberList;

@RestController
@RequiredArgsConstructor
public class ApiController {
    final Member member;
    private final MainService mainService;
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
    public ResDto join(@RequestBody ReqDto reqDto){
        ResDto resDto = new ResDto();

        mainService.create(reqDto);
        resDto.setStatus("success");
        resDto.setMessage("회원가입 성공!");

        return resDto;
    }

    @PostMapping("/login")
    public ResDto login(@RequestBody LoginReqDto loginReqDto){
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

        return resDto;
    }
}
