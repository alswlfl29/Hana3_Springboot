package com.study.Ex14ReadDB.dto;

import com.study.Ex14ReadDB.domain.board.Board;
import com.study.Ex14ReadDB.domain.reply.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReplyResponseDto {
    private Long replyIdx;
    private String replyName; // 댓글 작성자
    private String replyContent; // 댓글 내용
    private LocalDateTime replyDate; // 댓글 생성일시, 수정일시
    private Long replyBoardIdx; // 외래키, 게시글 인덱스

    //필드가 있는 생성자: Entity to Dto
    public ReplyResponseDto(Reply entity){
       this.replyIdx = entity.getReplyIdx();
       this.replyName = entity.getReplyName();
       this.replyContent = entity.getReplyContent();
       this.replyDate = entity.getReplyDate();
       this.replyBoardIdx = entity.getReplyBoardIdx();
    }
}
