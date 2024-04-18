package com.study.ProjectCompanyWeb.customer.dto;

import com.study.ProjectCompanyWeb.customer.domain.QnA;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class QnAResponseDto {
    private Integer qnaIdx;
    private String qnaName;
    private String qnaPw;
    private String qnaTitle;
    private String qnaContent;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate qnaDate = LocalDate.now();

    @Builder
    public QnAResponseDto(QnA entity) {
        this.qnaIdx = entity.getQnaIdx();
        this.qnaName = entity.getQnaName();
        this.qnaPw = entity.getQnaPw();
        this.qnaTitle = entity.getQnaTitle();
        this.qnaContent = entity.getQnaContent();
        this.qnaDate = entity.getQnaDate();
    }
}
