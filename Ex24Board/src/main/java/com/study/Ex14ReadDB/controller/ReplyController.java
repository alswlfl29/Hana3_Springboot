package com.study.Ex14ReadDB.controller;

import com.study.Ex14ReadDB.domain.reply.Reply;
import com.study.Ex14ReadDB.dto.ReplyResponseDto;
import com.study.Ex14ReadDB.dto.ReplySaveRequestDto;
import com.study.Ex14ReadDB.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
 * 웹 브라우저 -> HTTP 요청 -> WAS(톰캣, 서블릿 컨테이너) ->
 *  서블릿(Spring MVC) -> 디스패처 서블릿 -> 핸들러 맵퍼 -> Board Controller or Reply Controller
 *
 * interface Servlet(){
 *      init(), destroy(), service(request, response)
 * }
 */

@Controller
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {
    final private ReplyService replyService;

    @PostMapping("/writeReplyAction")
    @ResponseBody
    public String writeReplyAction(@ModelAttribute ReplySaveRequestDto dto,
                                   @RequestParam("replyBoardIdx") Long replyBoardIdx){
        Long newReplyIdx = replyService.save(dto);

        boolean isFound = replyService.existsById(newReplyIdx);

        if(isFound){
            return "<script>alert('댓글쓰기 성공'); location.href='/board/contentForm?boardIdx="+replyBoardIdx+"';</script>;";
        }else{
            return "<script>alert('댓글쓰기 실패'); history.back();</script>;";
        }
    }

    @GetMapping("/deleteAction")
    @ResponseBody
    public String deleteAction(@RequestParam("replyIdx") Long replyIdx,
                               @RequestParam("boardIdx") Long boardIdx){
        try {
            replyService.delete(replyIdx);
        }catch (Exception e){
            return "<script>alert('댓글삭제 실패'); history.back();</script>";
        }
        return "<script>alert('댓글삭제 성공'); location.href='/board/contentForm?boardIdx="+boardIdx+"';</script>;";
    }
}
