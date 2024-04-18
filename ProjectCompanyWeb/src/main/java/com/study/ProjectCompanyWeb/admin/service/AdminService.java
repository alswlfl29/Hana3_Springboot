package com.study.ProjectCompanyWeb.admin.service;

import com.study.ProjectCompanyWeb.admin.domain.AdminRepository;
import com.study.ProjectCompanyWeb.admin.dto.MemberResponseDto;
import com.study.ProjectCompanyWeb.member.domain.Member;
import com.study.ProjectCompanyWeb.member.domain.MemberRepository;
import com.study.ProjectCompanyWeb.member.dto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public boolean existsByMemberIdAndMemberPw(LoginRequestDto dto){
        return adminRepository.existsByMemberIdAndMemberPw(dto.getLoginID(), dto.getLoginPW());
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAllMember(){
        Sort sort = Sort.by(Sort.Direction.ASC, "memberId");
        List<Member> members = memberRepository.findAll(sort);
        return members.stream().map(MemberResponseDto::new).collect(Collectors.toList());
    }
}
