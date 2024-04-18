package com.study.ProjectCompanyWeb.community.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "company_notice")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noticeIdx;
    @Column(nullable = false)
    private String noticeTitle;
    @Column(nullable = false)
    private String noticeContent;
    @Column(nullable = false)
    private String noticeMemberId;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate noticeDate = LocalDate.now();

    @Builder
    public Community(String noticeTitle, String noticeContent, String noticeMemberId) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeMemberId = noticeMemberId;
        this.noticeDate = LocalDate.now();
    }
}
