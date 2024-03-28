package com.study.Ex09RestAPI;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// @RestController: @Controller + @ResponseBody
// @ResponseBody는 모든 메소드에 @ResponseBody를 붙이는 효과
// 즉, @Controller와 @ResponseBody를 따로 쓰는 것보다 @RestController를 쓰는게 더 나음
@RestController
@RequestMapping("/api/v1") // 경로의 공통적인 부분 한번만 작성
public class ApiController {
    public static List<Member> memberList = new ArrayList<>();

    // URL: localhost:8080/api/v1/login
    @PostMapping("/login")
    public ResDto login(@RequestBody ReqDto reqDto){
        System.out.println(reqDto.getUsername());
        System.out.println(reqDto.getPassword());

        // 로그인 로직
        // reqDto.username이 DB(리스트)에 있는지 확인
        boolean found = false;
        for(Member member:memberList){
            if(member.getUsername().equals(reqDto.getUsername())
            && member.getPassword().equals(reqDto.getPassword())){
                found = true; // 로그인 처리! 세션객체에 로그인 상태를 저장
                break;
            }
        }

        ResDto resDto = new ResDto();
        if(found){
            resDto.setStatus("ok");
            resDto.setMessage("로그인 성공!");
        }else{
            resDto.setStatus("fail");
            resDto.setMessage("로그인 실패!");
        }
        System.out.println(resDto);
        return resDto; // 클래스 객체가 JSON 응답(문자열)으로 전송된다.
    }

    // URL: localhost:8080/api/v1/join
    @PostMapping("/join")
    public void join(){

    }
}
