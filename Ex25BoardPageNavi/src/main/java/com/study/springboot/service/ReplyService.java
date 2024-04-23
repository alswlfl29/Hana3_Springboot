package com.study.springboot.service;

import com.study.springboot.domain.reply.Reply;
import com.study.springboot.domain.reply.ReplyRepository;
import com.study.springboot.dto.ReplyResponseDto;
import com.study.springboot.dto.ReplySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {
    final private ReplyRepository replyRepository;

    //중간에 예외(오류)발생시 rollup을 수행하여, 데이터 일관성을 유지해줌.
    @Transactional
    public Long save(final ReplySaveRequestDto dto){
        Reply entity = replyRepository.save( dto.toEntity() );
        return entity.getReplyIdx();
    }
    @Transactional(readOnly = true)
    public boolean existsById( Long replyIdx ){
        boolean isFound = replyRepository.existsById(replyIdx);
        return isFound;
    }
    @Transactional(readOnly = true)
    public List<ReplyResponseDto> findAllByReplyBoardIdx(Long boardIdx){
        //정렬기능 추가
        //Sort sort = Sort.by(Sort.Direction.DESC, "replyDate", "replyIdx");
        List<Reply> list = replyRepository.findAllByReplyBoardIdxOrderByReplyDateDesc( boardIdx );

        //List<Reply>를 List<ReplyResponseDto>로 변환 : stream()메소드 사용
        return list.stream().map(ReplyResponseDto::new).collect(Collectors.toList());
    }
    @Transactional
    public void delete(Long replyIdx){
        Reply entity = replyRepository.findById(replyIdx)
                .orElseThrow(() -> new IllegalArgumentException("댓글 인덱스 오류입니다. replyIdx:"+replyIdx) );
        replyRepository.delete( entity );
    }
}
