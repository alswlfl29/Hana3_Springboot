package com.study.ProjectCompanyWeb.admin.dto;

import com.study.ProjectCompanyWeb.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class MemberResponseDto {
    private String memberId;
    private String memberName;
    private String memberEmail;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate memberBirthDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate memberJoinDate = LocalDate.now();

    @Builder
    public MemberResponseDto(Member entity) {
        this.memberEmail = entity.getMemberEmail();
        this.memberId = entity.getMemberId();
        this.memberName = entity.getMemberName();
        this.memberBirthDate = entity.getMemberBirthDate();
        this.memberJoinDate = entity.getMemberJoinDate();
    }
}
