package com.study.springboot.dto;

import com.study.springboot.domain.board.Board;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class BoardSaveRequestDto {
    private String boardTitle;
    private String boardContent;
    private String boardName;
    private Long boardHit;

    //매개변수 있는 생성자
    @Builder
    public BoardSaveRequestDto(String boardTitle, String boardContent,
                               String boardName, Long boardHit) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardName = boardName;
        this.boardHit = boardHit;
    }
    public Board toEntity(){
        return Board.builder()
                .boardTitle(this.boardTitle)
                .boardContent(this.boardContent)
                .boardName(this.boardName)
                .boardHit(this.boardHit)
                .build();
    }
}
