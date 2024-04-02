package com.study.Pr07LoginJoin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
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

    @PostMapping("/dupli")
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
    public String join(RedirectAttributes rttr, JoinReqDto joinReqDto){
        this.memberService.create(joinReqDto);
        ResDto resDto = new ResDto("success","회원가입 성공!");

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
        if(memberService.isEmptyMembers()){
            ResDto resDto =new ResDto("fail","로그인 실패(회원 목록에 존재하지 않습니다.)");
            model.addAttribute("status",resDto.getStatus());
            model.addAttribute("message",resDto.getMessage());
        }else{
            if(memberService.isExistMember(loginReqDto)){
                if(memberService.isManager(loginReqDto)){
                    return "redirect:/list";
                }
                ResDto resDto =new ResDto("success","로그인 성공");
                model.addAttribute("status",resDto.getStatus());
                model.addAttribute("message",resDto.getMessage());
            }else{
                ResDto resDto =new ResDto("fail","로그인 실패(회원 목록에 존재하지 않습니다.)");
                model.addAttribute("status",resDto.getStatus());
                model.addAttribute("message",resDto.getMessage());
            }
        }
        return "login";
    }

    // 관리자화면
    @GetMapping("/list")
    public String list(Model model){
        List<Member> members = memberService.memberList();
        model.addAttribute("members",members);
        return "list";
    }

    // 수정화면
    @GetMapping("/update")
    public String update(@RequestParam int index, Model model ){
        Member member = memberService.getMemberData(index);
        model.addAttribute("member",member);
        model.addAttribute("index",index);
        return "update";
    }

    @PostMapping("/update")
    @ResponseBody
    public String update(UpdateReqDto updateReqDto){
        memberService.updateMember(updateReqDto);
        return "<script>alert('수정되었습니다.');location.href='/list';</script>";
    }

    // 삭제
    @GetMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam int index){
        memberService.deleteMember(index);
        return "<script>alert('삭제되었습니다.');location.href='/list';</script>";
    }
}
