package com.study.Ex12H2DB;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MemberRepository memberRepository;

    @GetMapping("/")
    public String main(Model model){
        memberRepository.save(new MemberEntity(Long.valueOf(1),
                "hong","1234","홍길동","ROLE_USER", LocalDate.now()));
        memberRepository.save(new MemberEntity(Long.valueOf(2),
                "tom","1234","톰아저씨","ROLE_USER", LocalDate.now()));
        memberRepository.save(new MemberEntity(Long.valueOf(3),
                "admin","1234","관리자","ROLE_ADMIN", LocalDate.now()));

        List<MemberEntity> list = memberRepository.findAll();
        for(MemberEntity m : list){
            System.out.println(m.getUser_name());
        }

        model.addAttribute("list",list);
        return "index";
    }
}
