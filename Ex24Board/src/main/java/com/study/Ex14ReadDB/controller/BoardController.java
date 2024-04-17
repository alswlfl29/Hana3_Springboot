package com.study.Ex14ReadDB.controller;

import com.study.Ex14ReadDB.domain.board.Board;
import com.study.Ex14ReadDB.dto.BoardResponseDto;
import com.study.Ex14ReadDB.dto.BoardSaveRequestDto;
import com.study.Ex14ReadDB.dto.ReplyResponseDto;
import com.study.Ex14ReadDB.service.BoardService;
import com.study.Ex14ReadDB.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final ReplyService replyService;

    // localhost:8080/board/
    @GetMapping("/")
    public String main(){
        return "redirect:/board/listForm";
        //servlet(JSP) request.forward() : request내장변수 간직, 주소줄 안바뀜.
        //             response.redirect() : request내장변수 소멸, 주소줄 바뀜.
    }
    @GetMapping("/listForm")
    public String listForm(Model model){
        List<BoardResponseDto> list = boardService.findAll();
        model.addAttribute("list", list);
        return "listForm"; //listForm.html로 응답
    }
    @GetMapping("/writeForm")
    public String writeForm(){
        return "writeForm"; //writeForm.html로 응답
    }
    //Form의 Input데이터를 가져오는 방법
    //1. @RequestParam
    //2. 클래스객체(DTO/VO) 매핑 : 기본생성자, Setter 필요
    //3. 맵(Map) 매핑
    @PostMapping("/writeAction")
    @ResponseBody
    public String writeAction(@ModelAttribute BoardSaveRequestDto dto){
        Long newIdx = boardService.save( dto );

        boolean isFound = boardService.existsById(newIdx);
        if( isFound ){
            return "<script>alert('글쓰기 성공'); location.href='/board/listForm';</script>";
        }else{
            return "<script>alert('글쓰기 실패'); history.back();</script>";
        }
    }
    @GetMapping("/contentForm")
    public String contentForm(@RequestParam("boardIdx") Long boardIdx,
                              Model model){
        System.out.println("boardIdx:"+boardIdx);

        //게시글 정보 조회
        BoardResponseDto dto = boardService.findById( boardIdx );
        //조회수 증가
        boardService.updateHit(boardIdx, dto.getBoardHit() + 1);
        dto.setBoardHit(dto.getBoardHit() + 1);
        model.addAttribute("dto", dto);

        // 댓글 리스트
        List<ReplyResponseDto> replyList = replyService.findAllByReplyBoardIdx(boardIdx);
        model.addAttribute("replyList", replyList);

        return "contentForm"; //contentForm.html로 응답
    }

    @PostMapping("/updateAction")
    @ResponseBody
    public String updateAction(@ModelAttribute BoardSaveRequestDto dto,
                               @RequestParam("boardIdx") Long boardIdx){
        Board entity = boardService.update(boardIdx, dto);
        if(entity.getBoardIdx() == boardIdx){
            // 업데이트 성공
            return "<script>alert('글수정 성공'); location.href='/board/contentForm?boardIdx="+boardIdx+"';</script>";
        }else{
            // 업데이트 실패
            return "<script>alert('글수정 실패'); history.back();</script>";
        }
    }

    @GetMapping("/deleteAction")
    @ResponseBody
    public String deleteAction(@RequestParam("boardIdx") Long boardIdx){
        boardService.delete(boardIdx);
        return "<script>alert('글삭제 성공'); location.href='/';</script>";
    }
}