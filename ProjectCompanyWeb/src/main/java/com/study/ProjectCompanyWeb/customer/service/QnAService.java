package com.study.ProjectCompanyWeb.customer.service;

import com.study.ProjectCompanyWeb.customer.domain.QnA;
import com.study.ProjectCompanyWeb.customer.domain.QnARepository;
import com.study.ProjectCompanyWeb.customer.dto.QnAResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QnAService {
    private final QnARepository qnARepository;

    @Transactional(readOnly = true)
    public List<QnAResponseDto> getQnAList(){
        Sort sort = Sort.by(Sort.Direction.DESC, "qnaDate","qnaIdx");
        List<QnA> list = qnARepository.findAll(sort);
        return list.stream().map(QnAResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<QnAResponseDto> getQnATitle(String title){
        List<QnA> list = qnARepository.findByQnaTitle(title);
        return list.stream().map(QnAResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<QnAResponseDto> getQnAContent(String content){
        List<QnA> list = qnARepository.findByQnaContent(content);
        return list.stream().map(QnAResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<QnAResponseDto> getQnAMember(String name){
        List<QnA> list = qnARepository.findByQnaName(name);
        return list.stream().map(QnAResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public boolean existsByQnaIdxAndQnaPw(Integer qnaIdx, String qnaPw){
        Integer newIdx = qnARepository.findById(qnaIdx)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시글입니다.")).getQnaIdx();
        return qnARepository.existsByQnaIdxAndQnaPw(newIdx, qnaPw);
    }

    @Transactional(readOnly = true)
    public QnAResponseDto getQnA(Integer qnaIdx){
        QnA qna = qnARepository.findById(qnaIdx)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시글입니다."));
        return QnAResponseDto.builder().entity(qna).build();
    }
}
