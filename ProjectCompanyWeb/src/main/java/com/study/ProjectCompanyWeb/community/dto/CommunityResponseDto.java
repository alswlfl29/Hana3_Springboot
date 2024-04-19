package com.study.ProjectCompanyWeb.community.dto;

import com.study.ProjectCompanyWeb.community.domain.Community;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CommunityResponseDto {
    private Integer noticeIdx;
    private String noticeTitle;
    private String noticeContent;
    private String noticeMemberId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate noticeDate;

    @Builder
    public CommunityResponseDto(Community entity){
        this.noticeIdx = entity.getNoticeIdx();
        this.noticeTitle = entity.getNoticeTitle();
        this.noticeContent = entity.getNoticeContent();
        this.noticeMemberId = entity.getNoticeMemberId();
        this.noticeDate = entity.getNoticeDate();
    }
}
