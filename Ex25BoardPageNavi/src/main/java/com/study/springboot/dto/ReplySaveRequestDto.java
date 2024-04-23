package com.study.springboot.dto;

import com.study.springboot.domain.reply.Reply;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ReplySaveRequestDto {
    private String replyContent;
    private String replyName;
    private Long replyBoardIdx; //외래키 - 게시글 인덱스

    @Builder
    public ReplySaveRequestDto(String replyContent, String replyName,
                               Long replyBoardIdx) {
        this.replyContent = replyContent;
        this.replyName = replyName;
        this.replyBoardIdx = replyBoardIdx;
    }
    public Reply toEntity(){
        return Reply.builder()
                .replyContent(this.replyContent)
                .replyName(this.replyName)
                .replyBoardIdx(this.replyBoardIdx)
                .build();
    }
}
