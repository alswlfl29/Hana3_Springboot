package com.study.Pr07LoginJoin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public boolean checkDuplication(String name){
        List<Member> members = memberRepository.getMembers();
        for(Member member:members){
            if(member.checkDuplication(name)) {
                return true;
            }
        }
        return false;
    }

    public void create(JoinReqDto reqDto){
        Member member = Member.builder()
                .username(reqDto.getInputName())
                .email(reqDto.getInputEmail())
                .password(reqDto.getInputPw())
                .joinDate(LocalDate.now())
                .build();
        this.memberRepository.save(member);
    }

    public boolean isEmpty(){
        List<Member> members = memberRepository.getMembers();
        return members.isEmpty();
    }

    public boolean checkIsMember(LoginReqDto loginReqDto){
        List<Member> members = memberRepository.getMembers();
        for(Member member:members){
            if(member.isMember(loginReqDto.getInputName(), loginReqDto.getInputPw())){
                return true;
            }
        }
        return false;
    }
}
