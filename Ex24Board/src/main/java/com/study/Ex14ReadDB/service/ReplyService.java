package com.study.Ex14ReadDB.service;

import com.study.Ex14ReadDB.domain.board.Board;
import com.study.Ex14ReadDB.domain.reply.Reply;
import com.study.Ex14ReadDB.domain.reply.ReplyRepository;
import com.study.Ex14ReadDB.dto.BoardResponseDto;
import com.study.Ex14ReadDB.dto.ReplyResponseDto;
import com.study.Ex14ReadDB.dto.ReplySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {
    final private ReplyRepository replyRepository;

    @Transactional(readOnly = true)
    public List<ReplyResponseDto> findAllByReplyBoardIdx(Long replyBoardIdx){
        // Sort sort = Sort.by(Sort.Direction.DESC, "replyDate", "replyIdx");
        List<Reply> list = replyRepository.findAllByReplyBoardIdxOrderByReplyDateDesc( replyBoardIdx );

        return list.stream().map(ReplyResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Reply findById(final Long replyIdx){
        return replyRepository.findById(replyIdx).orElseThrow(NullPointerException::new);
    }

    // 중간에 예외(오류)발생시 rollup 수행하여, 데이터 일관성 유지해줌.
    @Transactional
    public Long save(final ReplySaveRequestDto dto){
        Reply entity = replyRepository.save(dto.toEntity());
        return entity.getReplyIdx();
    }

    @Transactional(readOnly = true)
    public Boolean existsById(Long replyIdx){
        return replyRepository.existsById(replyIdx);
    }

    @Transactional
    public void delete(Long replyIdx){
        Reply entity = replyRepository.findById(replyIdx)
                .orElseThrow(()->new IllegalArgumentException("댓글 인덱스 오류입니다. replyIdx:"+replyIdx));
        replyRepository.delete(entity);
    }

}
