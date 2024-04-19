package com.study.ProjectCompanyWeb.member.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Boolean existsByMemberId(String memberId);

    Boolean existsByMemberIdx(Integer memberIdx);

    Optional<Member> findByMemberId(String memberId);

    Boolean existsByMemberIdAndMemberPw(String memberId, String memberPw);

    Optional<Member> findByMemberNameAndMemberEmail(String memberName, String memberEmail);

    Optional<Member> findByMemberNameAndMemberIdAndMemberEmail(String memberName, String memberId, String memberEmail);

    @Query(value="SELECT m FROM Member m " +
            "WHERE UPPER(m.memberId) Like UPPER('%' || :searchKeyword || '%') OR UPPER(m.memberName) Like UPPER('%' || :searchKeyword || '%') OR UPPER(m.memberEmail) Like UPPER('%' || :searchKeyword || '%')")
    List<Member> findAllMemberByNone(String searchKeyword, Sort sort);

    @Query(value="SELECT m FROM Member m WHERE UPPER(m.memberId) Like UPPER('%' || :searchKeyword || '%')")
    List<Member> findAllMemberByMemberId(String searchKeyword, Sort sort);

    @Query(value="SELECT m FROM Member m WHERE UPPER(m.memberName) Like UPPER('%' || :searchKeyword || '%')")
    List<Member> findAllMemberByMemberName(String searchKeyword, Sort sort);

    @Query(value="SELECT m FROM Member m WHERE UPPER(m.memberEmail) Like UPPER('%' || :searchKeyword || '%')")
    List<Member> findAllMemberByMemberEmail(String searchKeyword, Sort sort);

    @Query(value="SELECT m FROM Member m " +
            "WHERE UPPER(m.memberId) Like UPPER('%' || :searchKeyword || '%') OR UPPER(m.memberName) Like UPPER('%' || :searchKeyword || '%') OR UPPER(m.memberEmail) Like UPPER('%' || :searchKeyword || '%')")
    List<Member> findAllMemberByNoneLimit(String searchKeyword, Pageable pageable);

    @Query(value="SELECT m FROM Member m " +
            "WHERE UPPER(m.memberId) Like UPPER('%' || :searchKeyword || '%')")
    List<Member> findAllMemberByMemberIdLimit(String searchKeyword, Pageable pageable);

    @Query(value="SELECT m FROM Member m " +
            "WHERE UPPER(m.memberName) Like UPPER('%' || :searchKeyword || '%')")
    List<Member> findAllMemberByMemberNameLimit(String searchKeyword, Pageable pageable);

    @Query(value="SELECT m FROM Member m " +
            "WHERE UPPER(m.memberEmail) Like UPPER('%' || :searchKeyword || '%')")
    List<Member> findAllMemberByMemberEmailLimit(String searchKeyword, Pageable pageable);

    @Query(value="SELECT m FROM Member m")
    List<Member> findAllLimit(Pageable pageable);

}
