package com.study.ProjectCompanyWeb.member.service;

import com.study.ProjectCompanyWeb.member.domain.Member;
import com.study.ProjectCompanyWeb.member.domain.MemberRepository;
import com.study.ProjectCompanyWeb.member.dto.JoinRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public boolean checkDuplicateId(String memberId){
        return !memberRepository.existsByMemberId(memberId);
    }

    @Transactional
    public Integer save(final JoinRequestDto dto){
        Member entity = memberRepository.save(dto.toEntity());
        return entity.getMemberIdx();
    }

    @Transactional(readOnly = true)
    public boolean existsByMemberIdx(Integer memberIdx){
        return memberRepository.existsByMemberIdx(memberIdx);
    }

    @Transactional(readOnly = true)
    public String findByMemberId(String memberId){
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 아이디입니다."));
        return member.getMemberId();
    }

    @Transactional(readOnly = true)
    public boolean existsByMemberIdAndMemberPw(String memberId, String memberPw){
        return memberRepository.existsByMemberIdAndMemberPw(memberId,memberPw);
    }

    @Transactional(readOnly = true)
    public String findByMemberNameAndMemberEmail(String memberName, String memberEmail){
        Member member = memberRepository.findByMemberNameAndMemberEmail(memberName, memberEmail)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원입니다."));
        return member.getMemberId();
    }

    @Transactional(readOnly = true)
    public String findByMemberNameAndMemberIdAndMemberEmail(String memberName, String memberId, String memberEmail){
        Member member = memberRepository.findByMemberNameAndMemberIdAndMemberEmail(memberName, memberId, memberEmail)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        return member.getMemberPw();
    }
}
