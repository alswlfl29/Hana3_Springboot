package com.study.ProjectCompanyWeb.admin.dto;

import com.study.ProjectCompanyWeb.community.domain.Community;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommunitySaveRequestDto {
    private String noticeTitle;
    private String editor4;
    private String noticeMemberId;

    @Builder
    public CommunitySaveRequestDto(String noticeTitle, String editor4, String noticeMemberId) {
        this.noticeTitle = noticeTitle;
        this.editor4 = editor4;
        this.noticeMemberId = noticeMemberId;
    }

    public Community toEntity(){
        return Community.builder()
                .noticeTitle(this.noticeTitle)
                .noticeContent(this.editor4)
                .noticeMemberId(this.noticeMemberId)
                .build();
    }

}
