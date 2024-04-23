package com.study.springboot.dto;

import com.study.springboot.domain.reply.Reply;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReplyResponseDto {
    private Long replyIdx;
    private String replyContent; //댓글 내용
    private String replyName; //댓글 작성자
    private LocalDateTime replyDate; //댓글 생성일시,수정일시
    private Long replyBoardIdx; //외래키, 게시글 인덱스

    public ReplyResponseDto(Reply entity){
        this.replyIdx = entity.getReplyIdx();
        this.replyContent = entity.getReplyContent();
        this.replyName = entity.getReplyName();
        this.replyDate = entity.getReplyDate();
        this.replyBoardIdx = entity.getReplyBoardIdx();
    }
}
