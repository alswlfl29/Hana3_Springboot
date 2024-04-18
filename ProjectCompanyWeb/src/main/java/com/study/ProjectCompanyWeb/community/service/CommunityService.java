package com.study.ProjectCompanyWeb.community.service;

import com.study.ProjectCompanyWeb.community.domain.CommunityRepository;
import com.study.ProjectCompanyWeb.community.domain.Community;
import com.study.ProjectCompanyWeb.community.dto.CommunityResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private final CommunityRepository communityRepository;

    @Transactional(readOnly = true)
    public List<CommunityResponseDto> getCommunityList(){
        Sort sort = Sort.by(Sort.Direction.DESC, "noticeDate","noticeIdx");
        List<Community> list = communityRepository.findAll(sort);
        return list.stream().map(CommunityResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommunityResponseDto> getCommunityTitle(String title){
        List<Community> list = communityRepository.findByNoticeTitle(title);
        return list.stream().map(CommunityResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommunityResponseDto> getCommunityContent(String content){
        List<Community> list = communityRepository.findByNoticeContent(content);
        return list.stream().map(CommunityResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CommunityResponseDto getCommunityItem(Integer no){
        Community community = communityRepository.findById(no)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시글입니다."));
        return CommunityResponseDto.builder()
                .entity(community)
                .build();
    }
}
