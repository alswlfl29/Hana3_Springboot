package com.study.ProjectCompanyWeb.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "company_member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberIdx;
    @Column(nullable = false)
    private String memberId;
    @Column(nullable = false)
    private String memberPw;
    @Column(nullable = false)
    private String memberName;
    @Column(nullable = false)
    private String memberEmail;
    @Column(nullable = false)
    private int memberEmailReceive;
    @Column(nullable = false)
    private int memberPwQuestion;
    @Column(nullable = false)
    private String memberPwAnswer;
    @Column(nullable = false)
    private String memberGender;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate memberBirthDate;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate memberJoinDate = LocalDate.now();

    @Builder
    public Member(String memberId, String memberPw, String memberName, String memberEmail, int memberEmailReceive, int memberPwQuestion, String memberPwAnswer, String memberGender, LocalDate memberBirthDate) {
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberEmailReceive = memberEmailReceive;
        this.memberPwQuestion = memberPwQuestion;
        this.memberPwAnswer = memberPwAnswer;
        this.memberGender = memberGender;
        this.memberBirthDate = memberBirthDate;
    }
}
