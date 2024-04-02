package com.study.Pr07LoginJoin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public boolean checkDuplication(String name){
        List<Member> members = memberRepository.getMembers();
        for(Member member:members){
            if(member.isDuplicateName(name)) {
                return true;
            }
        }
        return false;
    }

    public void create(JoinReqDto joinReqDto){
        Member member = Member.builder()
                .username(joinReqDto.getInputName())
                .password(joinReqDto.getInputPw())
                .email(joinReqDto.getInputEmail())
                .joinDate(LocalDate.now())
                .build();
        this.memberRepository.save(member);
    }

    public boolean isEmptyMembers(){
        List<Member> members = memberRepository.getMembers();
        return members.isEmpty();
    }

    public boolean isExistMember(LoginReqDto loginReqDto) {
        List<Member> members = memberRepository.getMembers();
        for (Member member : members) {
            if (member.isExistMember(loginReqDto.getInputName(), loginReqDto.getInputPw())) {
                return true;
            }
        }
     return false;
    }

    public boolean isManager(LoginReqDto loginReqDto){
        return memberRepository.isManager(loginReqDto.getInputName(), loginReqDto.getInputPw());
    }

    public List<Member> memberList(){
        return new ArrayList<>(memberRepository.getMembers());
    }

    public Member getMemberData(int index){
        return memberRepository.getMembers().get(index);
    }

    public void updateMember(UpdateReqDto updateReqDto){
        Member member = Member.builder()
                .username(updateReqDto.getInputName())
                .password(updateReqDto.getInputPw())
                .email(updateReqDto.getInputEmail())
                .joinDate(updateReqDto.getInputJoindate())
                .build();
        this.memberRepository.update(updateReqDto.getIndex(), member);
    }
    public void deleteMember(int index){
        this.memberRepository.delete(index);
    }
}
