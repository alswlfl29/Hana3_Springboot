package com.study.Ex14ReadDB.dto;

import com.study.Ex14ReadDB.domain.reply.Reply;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplySaveRequestDto {
    private String replyContent;
    private String replyName;
    private Long replyBoardIdx; // 외래키 - 게시글 인덱스

    public Reply toEntity(){
        return Reply.builder()
                .replyContent(this.replyContent)
                .replyName(this.replyName)
                .replyBoardIdx(this.replyBoardIdx)
                .build();
    }
}
