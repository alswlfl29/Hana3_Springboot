package com.study.ProjectCompanyWeb.member.dto;

import com.study.ProjectCompanyWeb.member.domain.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class JoinRequestDto {
    private String memberId;
    private String memberPw;
    private String memberPw2;
    private String memberName;
    private String memberEmail;
    private int memberEmailReceive;
    private int memberPwQuestion;
    private String memberPwAnswer;
    private String memberGender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate memberBirthDate;

    public Member toEntity(){
        return Member.builder()
                .memberId(this.memberId)
                .memberPw(this.memberPw)
                .memberName(this.memberName)
                .memberEmail(this.memberEmail)
                .memberEmailReceive(this.memberEmailReceive)
                .memberPwQuestion(this.memberPwQuestion)
                .memberPwAnswer(this.memberPwAnswer)
                .memberGender(this.memberGender)
                .memberBirthDate(this.memberBirthDate)
                .build();
    }
}
