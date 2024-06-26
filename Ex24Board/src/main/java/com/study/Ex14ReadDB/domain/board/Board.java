package com.study.Ex14ReadDB.domain.board;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// @Entity, @Component: 기본생성자가 필요함. 없으면 자동생성
// fastxml ObjectMapper에서 Class 정보를 이용하여 리플렉션을 할 때 기본생성자로 생성함.
// 매개변수가 있는 생성자: 매개변수의 정보를 알 수 없기 때문에 자동생성 할 수 없다.
// 이게있다면, 기본생성자를 강제로 만들어야 함.
@Entity
@Table(name = "board")
@Getter
// new Board() 작성이 불가하여, 객체의 일관성 유지에 좋다.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_idx", nullable = false)
    private Long boardIdx;
    @Column(name = "board_name", nullable = false)
    private String boardName; //작성자
    @Column(name = "board_title", nullable = false)
    private String boardTitle; //제목
    @Column(name = "board_content", nullable = false)
    private String boardContent; //내용
    @Column(name = "board_hit", nullable = false)
    private Long boardHit; //조회수
    @Column(name = "board_date", nullable = false)
    // @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private LocalDateTime boardDate = LocalDateTime.now(); //생성일시,수정일시

    //매개변수가 있는 생성자
    @Builder
    public Board(String boardName, String boardTitle,
                 String boardContent, Long boardHit) {
        this.boardName = boardName;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardHit = boardHit;
    }

    public void update(String boardName, String boardTitle,
                       String boardContent, Long boardHit) {
        this.boardName = boardName;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardHit = boardHit;
        this.boardDate = LocalDateTime.now(); //현재시간으로 업데이트
    }

    public void updateHit(Long boardHit) {
        this.boardHit = boardHit;
    }

}