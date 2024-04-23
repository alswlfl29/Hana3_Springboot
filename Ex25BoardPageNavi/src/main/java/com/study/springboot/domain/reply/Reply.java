package com.study.springboot.domain.reply;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //Entity e = new Entity()
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_idx", nullable = false)
    private Long replyIdx;
    @Column(name = "reply_name", nullable = false)
    private String replyName; //작성자
    @Column(name = "reply_content", nullable = false)
    private String replyContent; //내용
    @Column(name = "reply_date", nullable = false)
    private LocalDateTime replyDate = LocalDateTime.now();//생성일시,수정일시
    @Column(name = "reply_board_idx", nullable = false)
    private Long replyBoardIdx; //외래키

    @Builder
    public Reply(String replyName, String replyContent, Long replyBoardIdx) {
        this.replyName = replyName;
        this.replyContent = replyContent;
        this.replyBoardIdx = replyBoardIdx;
    }
    public void update(String replyContent, String replyName){
        this.replyContent = replyContent;
        this.replyName = replyName;
        this.replyDate = LocalDateTime.now();
    }
}

