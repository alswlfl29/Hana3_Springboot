package com.study.springboot.controller;

import com.study.springboot.dto.BoardResponseDto;
import com.study.springboot.dto.BoardSaveRequestDto;
import com.study.springboot.dto.ReplyResponseDto;
import com.study.springboot.service.BoardService;
import com.study.springboot.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiController {
    final private BoardService boardService;
    final private ReplyService replyService;

    // 게시글 전체 조회
    // URI: GET localhost:8080/api/boards
    @GetMapping("/boards")
    public List<BoardResponseDto> findAll(){
        return boardService.findAll();
    }
    // 게시글 단건 조회
    // URI: GET localhost:8080/api/boards/321
    @GetMapping("/boards/{id}")
    public BoardResponseDto save(@PathVariable final Long id){
        return boardService.findById(id);
    }

    //게시글 댓글 조회
    //URI : GET localhost:8080/api/boards/321/replys
    @GetMapping("/boards/{id}/replys")
    public List<ReplyResponseDto> replys(@PathVariable final Long id){
        return replyService.findAllByReplyBoardIdx( id );
    }
    //게시글 추가하기
    //URI : POST localhost:8080/api/boards
    @PostMapping("/boards")
    public BoardResponseDto save(@RequestBody final BoardSaveRequestDto dto) {
        return boardService.saveToDto(dto);
    }
    //게시글 수정하기
    //URI : PUT/PATCH localhost:8080/api/boards/321
    @PutMapping("/boards/{id}")
    public BoardResponseDto update(@PathVariable final Long id, @RequestBody final BoardSaveRequestDto dto) {
        return boardService.updateToDto(id, dto);
    }
    //게시글 삭제하기
    //URI : DELETE localhost:8080/api/boards/321
    @DeleteMapping("/boards/{id}")
    public Long delete(@PathVariable final Long id){
        return boardService.deleteToLong( id );
    }

}
