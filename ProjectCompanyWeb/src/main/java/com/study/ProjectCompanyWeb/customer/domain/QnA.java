package com.study.ProjectCompanyWeb.customer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "company_qna")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QnA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer qnaIdx;
    @Column(nullable = false)
    private String qnaName;
    @Column(nullable = false)
    private String qnaPw;
    @Column(nullable = false)
    private String qnaTitle;
    @Column(nullable = false)
    private String qnaContent;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate qnaDate = LocalDate.now();

    @Builder
    public QnA(String qnaName, String qnaPw, String qnaTitle, String qnaContent) {
        this.qnaName = qnaName;
        this.qnaPw = qnaPw;
        this.qnaTitle = qnaTitle;
        this.qnaContent = qnaContent;
    }
}
