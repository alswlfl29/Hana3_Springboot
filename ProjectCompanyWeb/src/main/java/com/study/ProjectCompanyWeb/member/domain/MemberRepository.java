package com.study.ProjectCompanyWeb.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Boolean existsByMemberId(String memberId);

    Boolean existsByMemberIdx(Integer memberIdx);

    Optional<Member> findByMemberId(String memberId);

    Boolean existsByMemberIdAndMemberPw(String memberId, String memberPw);

    Optional<Member> findByMemberNameAndMemberEmail(String memberName, String memberEmail);

    Optional<Member> findByMemberNameAndMemberIdAndMemberEmail(String memberName, String memberId, String memberEmail);
}
