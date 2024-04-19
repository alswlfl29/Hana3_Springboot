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
    public List<CommunityResponseDto> findAllCommunity(){
        Sort sort = Sort.by(Sort.Direction.DESC, "noticeDate");
        List<Community> members = communityRepository.findAll(sort);
        return members.stream().map(CommunityResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommunityResponseDto> findAllCommunityByNoticeTitle(String search_keyword){
        Sort sort = Sort.by(Sort.Direction.DESC, "noticeDate");
        List<Community> communities = communityRepository.findAllCommunityByNoticeTitle(search_keyword, sort);
        return communities.stream().map(CommunityResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommunityResponseDto> findAllCommunityByNoticeContent(String search_keyword){
        Sort sort = Sort.by(Sort.Direction.DESC, "noticeDate");
        List<Community> communities = communityRepository.findAllCommunityByNoticeContent(search_keyword, sort);
        return communities.stream().map(CommunityResponseDto::new).collect(Collectors.toList());
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
