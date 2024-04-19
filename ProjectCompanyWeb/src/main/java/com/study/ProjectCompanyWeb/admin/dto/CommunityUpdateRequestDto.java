package com.study.ProjectCompanyWeb.admin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommunityUpdateRequestDto {
    private String editor4;

    @Builder
    public CommunityUpdateRequestDto(String editor4) {
        this.editor4 = editor4;
    }
}
