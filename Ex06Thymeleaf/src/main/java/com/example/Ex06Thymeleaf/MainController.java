package com.example.Ex06Thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(){
        // return "forward:index";
        return "redirect:index";
        // forward: 내부(세션, 모델) 변수값을 가지고 페이지 이동(내부 URL로 이동시)
        // redirect: 내부 변수값을 버리고 페이지 이동(타 사이트로 이동시)
    }
    @GetMapping("/index")
    public String index(Model model){
        // Model 클래스: 스프링에서 데이터를 담아서 전달하는 용도의 클래스 객체
        // 서블릿 메소드에 매개변수로 선언하면, 스프링 컨테이너(컨텍스트)에서 자동 주입됨.
        model.addAttribute("name_text","홍길동");
        model.addAttribute("name_html","<ins>홍길동</ins>");
        model.addAttribute("server_value","HONG");
        return "index"; // index.html 동적HTML로 응답함.
    }

    // FrontEnd - HTML 에서 서버로 데이터를 전송하는 방법
    // HTML Form 태그: GET/POST 방식으로 submit 한다.
    // JavaScript Fetch() 함수: GET/POST/PUT/DELETE 방식으로 전송한다.
    // JQuery Lib에서 AJAX() 함수: GET/POST/PUT/DELETE 방식으로 전송한다.
    // Axios 모듈에서 axios() 함수: GET/POST/PUT/DELETE 방식으로 전송한다.
    // 디버깅툴: POSTMAN, THUNDER 등을 사용

    // GET 방식으로 요청 파라미터의 데이터를 받을 수 있다.
    // URI(URL): localhost:8080/index-param?id=hong&pw=1234
    //      id: hong
    //      pw: 1234
    // @RequestParam: 요청 파라미터를 가져오는 어노테이션.
    @GetMapping("/index-param")
    public String indexParam(@RequestParam String id, @RequestParam String pw, Model model){
        System.out.println("id: "+id);
        System.out.println("pw: "+pw);
        model.addAttribute("id",id);
        model.addAttribute("pw",pw);
        return "index";
    }

    @PostMapping("/index-param")
    public String indexParamPost(@RequestParam String id, @RequestParam String pw, Model model){
        System.out.println("id: "+id);
        System.out.println("pw: "+pw);
        model.addAttribute("id",id);
        model.addAttribute("pw",pw);
        return "index";
    }
}
