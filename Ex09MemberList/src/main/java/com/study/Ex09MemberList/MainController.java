package com.study.Ex09MemberList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    //ArrayList 선언
    List<Member> memberList = new ArrayList<>();

    @GetMapping("/")
    public String main(){
        return "index";
    }
    //html의 name속성을 지정, 변수이름을 다르게 설정가능.
    //@RequestParam("inputName")
    //@RequestParam(name="inputName") String nickname;
    //@RequestParam(defaultValue = "홍길동") //기본값 (값이 없을때)
    //              required = true //NonNull이 기본값(null일때 오류)
    //              required = false //null을 허용함.
    @PostMapping("/join")
    public String join(@RequestParam(defaultValue = "홍길동", required = false) String inputName,
                       @RequestParam String inputEmail,
                       @RequestParam String inputPw,
                       @RequestParam String inputPw2,
                       Model model){
        System.out.println("inputName:"+inputName);
        System.out.println("inputEmail:"+inputEmail);
        System.out.println("inputPw:"+inputPw);
        System.out.println("inputPw2:"+inputPw2);

        //DB에 저장한다.
        //ArrayList에 add한다.
        Member member = new Member();
        member.setName(inputName);
        member.setEmail(inputEmail);
        member.setPassword(inputPw);

        memberList.add(member);

        //model에 데이터 실어서 타임리프로 회원가입 되었음을 html표시한다.
        model.addAttribute("message", "회원가입 성공!");

        return "index";
    }

    //JS 객체(KV) <=====> Java 클래스객체와 연결된다.
    @PostMapping("/dupl")
    @ResponseBody
    public SendData dupl(@RequestBody ReceiveData data){

        System.out.println(data.getInputName());

        //inputName이 ArrayList내에 중복되는지 확인
        boolean isDupl = false;
        for( Member member : memberList ){
            if(member.getName().equals( data.getInputName() ) ){
                //중복됨
                isDupl = true;
                break;
            }
        }

        SendData sendData = new SendData();
        //중복된 이름이 있으면
        if( isDupl ){
            sendData.setStatus("ok");
            sendData.setMessage("중복된 아이디가 있습니다.");
        }else {//중복된 이름이 없으면
            sendData.setStatus("ok");
            sendData.setMessage("중복된 아이디가 없습니다.");
        }

        return sendData;
    }

    //http://localhost:8080/login-form
    @GetMapping("login-form")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/login")
    public String join(@RequestParam String inputName,
                       @RequestParam String inputPw,
                       Model model){
        System.out.println("inputName:"+inputName);
        System.out.println("inputPw:"+inputPw);

        //DB에 검색한다.
        //ArrayList에서 회원정보가 있는지 확인한다.
        boolean isLoginOk = false;
        for(Member member : memberList){
            if( member.getName().equals(inputName) &&
                    member.getPassword().equals(inputPw) ){
                //로그인 성공
                isLoginOk = true;
                break;
            }
        }
        //타임리프로 로그인 성공 여부를 html표시한다.
        if( isLoginOk ){
            model.addAttribute("message", "로그인 성공!");
            if( inputName.equals("admin") ){
                model.addAttribute("memberList", memberList);
                //관리자 페이지 이동
                return "admin";
            }
        }else{
            model.addAttribute("message", "로그인 실패!");
        }

        return "login";
    }

    //delete?index=0
    @GetMapping("/delete")
    public String delete(@RequestParam String index,
                         Model model){

        memberList.remove( Integer.parseInt(index) );

        model.addAttribute("memberList", memberList);

        return "admin";
    }
    //뒤로가기 제어
    //1.location.replace()함수 이용시 window.history에 남지 않음.
    //2.JS로 뒤로가기 할때 새로고침 되도록 한다.

    //삭제(액션) 후에 JS Alert를 띄운후, redirect/replace(페이지 이동) 하기
    @GetMapping("/delete2")
    @ResponseBody
    public String delete2(@RequestParam String index,
                          Model model){

        memberList.remove( Integer.parseInt(index) );

        return "<script>alert('삭제되었습니다.');location.href='/admin';</script>";
        //location.replace(), location.back()
    }

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("memberList", memberList);
        //관리자 페이지로 이동
        return "admin";
    }

    //업데이트 양식(폼)
    @GetMapping("/update-form")
    public String updateForm(@RequestParam String index, Model model){

        model.addAttribute("member", memberList.get(Integer.parseInt(index)));
        model.addAttribute("index", index);

        //업데이트 페이지로 이동
        return "update";
    }
    //업데이트 액션
    @PostMapping("/update-action")
    public String updateAction(@RequestParam String index,
                               @RequestParam String inputName,
                               @RequestParam String inputPw,
                               @RequestParam String inputEmail,
                               Model model){
        System.out.println("index:"+index);
        System.out.println("inputName:"+inputName);
        System.out.println("inputPw:"+inputPw);
        System.out.println("inputEmail:"+inputEmail);

        //memberList를 수정한다.
        int intIndex = Integer.parseInt(index);
        Member member = new Member();
        member.setName(inputName);
        member.setPassword(inputPw);
        member.setEmail(inputEmail);

        memberList.set(intIndex, member);

        //관리자 페이지로 리다이렉트(경로 재호출)
        return "redirect:admin";
    }

}