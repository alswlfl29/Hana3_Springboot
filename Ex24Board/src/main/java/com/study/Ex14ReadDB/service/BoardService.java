package com.study.Ex14ReadDB.service;

import com.study.Ex14ReadDB.domain.board.Board;
import com.study.Ex14ReadDB.domain.board.BoardRepository;
import com.study.Ex14ReadDB.dto.BoardResponseDto;
import com.study.Ex14ReadDB.dto.BoardSaveRequestDto;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// 서블릿(Servlet): Java 계열의 HTTP 요청과 응답을 처리하는 기능의 모음 또는 사양
//              : JDK에서는 interface Servlet 또는 class HttpServlet으로 구현한다.
//              : 기본적으로 init, destroy, service(요청/응답처리) 세가지 메소드
// HTTP 요청 -> WAS 톰캣(서블릿 컨테이너) -> 거대한 하나의 Servlet(SpringMVC)
//      --> 디스패치 서블릿 -> 핸들러 매퍼 -> 컨트롤러 -> 매핑 메소드(서블릿)
//      --> 서비스(Service) -> DTO -> DAO(Repository) -> Entity -> DB
// HTTP 응답 <- html(json) <- SpringMVC <- 뷰 리졸브(view resolver)
// <- 동적 html 파일 <- 디스패치 서블릿

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    //@Transactional
    //JPA를 사용한다면, 서비스(Service) 클래스에서 필수적으로 사용되어야 하는 어노테이션입니다.
    //일반적으로 메서드 레벨에 선언하게 되며, 메서드의 실행, 종료, 예외를 기준으로
    //각각 실행(begin), 종료(commit), 예외(rollback)를 자동으로 처리해 줍니다.
    // Transaction : 일련의 작업의 단위(단일작업의 모음-Select, Update, Insert, Delete, Drop, Alter)
    // 트랜잭션 시작 : 일련의 작업의 시작
    // 트랜잭션 종료 : 일련의 작업의 종료(커밋 발생 - 물리DB에 적용)
    // 트랜잭션 예외 : 일련의 작업중 예외발생(로백 발생 - 처음 작업시작전 상태로 되돌림)
    // 예) 은행 송금 처리(하나의 트랜잭션)
    //    1. A계좌 : 1000원 감소  -> 성공
    //    2. B계좌 : 1000원 증가  -> 통신오류!
    //    3. 송금 내역 저장
    @Transactional(readOnly = true)
    public List<BoardResponseDto> findAll(){ //전체 목록 조회
        //정렬기능 추가
        Sort sort = Sort.by(Sort.Direction.DESC, "boardDate", "boardIdx");
        List<Board> list = boardRepository.findAll( sort );

        //List<Board>를 List<BoardResponseDto>로 변환 : stream()메소드 사용
        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    //@Transactional : 일련의 배치작업 중 예외가 발생시 Rollback를 작동시킨다.
    @Transactional
    public Long save(final BoardSaveRequestDto dto){
        Board entity = boardRepository.save( dto.toEntity() );
        return entity.getBoardIdx();
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long boardIdx){
        return boardRepository.existsById( boardIdx );
    }

    @Transactional(readOnly = true)
    public BoardResponseDto findById(Long boardIdx){
        Board entity
                = boardRepository.findById( boardIdx )
                .orElseThrow( () -> new IllegalArgumentException(
                        "없는 글인덱스입니다.boardIdx:"+boardIdx));
        return new BoardResponseDto(entity);
    }
    @Transactional
    public Board updateHit(final Long boardIdx, final Long hit){
        Board entity = boardRepository.findById(boardIdx)
                .orElseThrow( () -> new IllegalArgumentException(
                        "없는 글인덱스입니다.boardIdx:"+boardIdx));
        entity.updateHit(hit);
        return entity;
    }

    @Transactional
    public Board update(final Long boardIdx, final BoardSaveRequestDto dto){
        Board entity = boardRepository.findById(boardIdx)
                .orElseThrow( () -> new IllegalArgumentException(
                        "없는 글인덱스입니다.boardIdx:"+boardIdx));
        entity.update(dto.getBoardName(), dto.getBoardTitle(),
                dto.getBoardContent(), dto.getBoardHit());
        //save()함수를 쓰지 않아도 update가 된다.
        //엔티티의 영속성 컨텍스트 속성으로 인해, update가 이루어짐.
        //@Transactional을 사용해야 됨.
        //예전의 방식) Board newEntity = boardRepository.save( entity );
        return entity;
    }

    @Transactional
    public void delete(final Long boardIdx){
        Board entity = boardRepository.findById(boardIdx)
                        .orElseThrow(()->new IllegalArgumentException("해당 글 인덱스가 없습니다. boardIdx: "+boardIdx));

        boardRepository.delete(entity);
    }
}
