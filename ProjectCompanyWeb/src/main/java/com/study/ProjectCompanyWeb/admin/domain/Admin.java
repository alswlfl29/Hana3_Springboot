package com.study.ProjectCompanyWeb.admin.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "company_member_admin")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin {
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate memberJoinDate = LocalDate.now();
}
