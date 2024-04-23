package com.study.ProjectCompanyWeb.admin.service;

import com.study.ProjectCompanyWeb.admin.domain.AdminRepository;
import com.study.ProjectCompanyWeb.admin.dto.CommunitySaveRequestDto;
import com.study.ProjectCompanyWeb.admin.dto.CommunityUpdateRequestDto;
import com.study.ProjectCompanyWeb.admin.dto.MemberResponseDto;
import com.study.ProjectCompanyWeb.community.domain.Community;
import com.study.ProjectCompanyWeb.community.domain.CommunityRepository;
import com.study.ProjectCompanyWeb.community.dto.CommunityResponseDto;
import com.study.ProjectCompanyWeb.member.domain.Member;
import com.study.ProjectCompanyWeb.member.domain.MemberRepository;
import com.study.ProjectCompanyWeb.member.dto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final MemberRepository memberRepository;
    private final CommunityRepository communityRepository;

    @Transactional(readOnly = true)
    public boolean existsByMemberIdAndMemberPw(LoginRequestDto dto){
        return adminRepository.existsByMemberIdAndMemberPw(dto.getLoginID(), dto.getLoginPW());
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAllMember(){
        Sort sort = Sort.by(Sort.Direction.ASC, "memberId");
        List<Member> members = memberRepository.findAll(sort);
        return members.stream().map(MemberResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAllMemberByNoneOrderBy(String search_keyword, String order_select, String page_select){
        String[] orderSelect = order_select.split("_");
        Sort sort = Sort.by(orderSelect[1].equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, orderSelect[0]);
        List<Member> members;
        if(page_select.equals("all")){
            members = memberRepository.findAllMemberByNone(search_keyword, sort);
        }else{
            members = memberRepository.findAllMemberByNoneLimit(search_keyword, PageRequest.of(0, Integer.parseInt(page_select), sort));
        }
        return members.stream().map(MemberResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAllMemberByMemberIdOrderBy(String search_keyword, String order_select, String page_select){
        String[] orderSelect = order_select.split("_");
        Sort sort = Sort.by(orderSelect[1].equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, orderSelect[0]);
        List<Member> members;
        if(page_select.equals("all")){
            members = memberRepository.findAllMemberByMemberId(search_keyword, sort);
        }else{
            members = memberRepository.findAllMemberByMemberIdLimit(search_keyword, PageRequest.of(0, Integer.parseInt(page_select), sort));
        }
        return members.stream().map(MemberResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAllMemberByMemberNameOrderBy(String search_keyword, String order_select, String page_select){
        String[] orderSelect = order_select.split("_");
        Sort sort = Sort.by(orderSelect[1].equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, orderSelect[0]);
        List<Member> members;
        if(page_select.equals("all")){
            members = memberRepository.findAllMemberByMemberName(search_keyword, sort);
        }else{
            members = memberRepository.findAllMemberByMemberNameLimit(search_keyword, PageRequest.of(0, Integer.parseInt(page_select), sort));
        }
        return members.stream().map(MemberResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAllMemberByMemberEmailOrderBy(String search_keyword, String order_select, String page_select){
        String[] orderSelect = order_select.split("_");
        Sort sort = Sort.by(orderSelect[1].equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, orderSelect[0]);
        List<Member> members;
        if(page_select.equals("all")){
            members = memberRepository.findAllMemberByMemberEmail(search_keyword, sort);
        }else{
            members = memberRepository.findAllMemberByMemberEmailLimit(search_keyword, PageRequest.of(0, Integer.parseInt(page_select), sort));
        }
        return members.stream().map(MemberResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAllMemberOrderBy(String order_select, String page_select){
        String[] orderSelect = order_select.split("_");
        Sort sort = Sort.by(orderSelect[1].equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, orderSelect[0]);
        List<Member> members;
        if(page_select.equals("all")){
            members = memberRepository.findAll(sort);
        }else{
            members = memberRepository.findAllLimit(PageRequest.of(0, Integer.parseInt(page_select), sort));
        }
        return members.stream().map(MemberResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommunityResponseDto> findAllCommunity(){
        Sort sort = Sort.by(Sort.Direction.ASC, "noticeIdx");
        List<Community> members = communityRepository.findAll(sort);
        return members.stream().map(CommunityResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommunityResponseDto> findAllCommunityByNoneOrderBy(String search_keyword, String order_select, String page_select){
        String[] orderSelect = order_select.split("_");
        Sort sort = Sort.by(orderSelect[1].equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, orderSelect[0]);
        List<Community> communities;
        if(page_select.equals("all")){
            communities = communityRepository.findAllCommunityByNone(search_keyword, sort);
        }else{
            communities = communityRepository.findAllCommunityByNoneLimit(search_keyword, PageRequest.of(0, Integer.parseInt(page_select), sort));
        }
        return communities.stream().map(CommunityResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommunityResponseDto> findAllCommunityByNoticeTitleOrderBy(String search_keyword, String order_select, String page_select){
        String[] orderSelect = order_select.split("_");
        Sort sort = Sort.by(orderSelect[1].equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, orderSelect[0]);
        List<Community> communities;
        if(page_select.equals("all")){
            communities = communityRepository.findAllCommunityByNoticeTitle(search_keyword, sort);
        }else{
            communities = communityRepository.findAllCommunityByNoticeTitleLimit(search_keyword, PageRequest.of(0, Integer.parseInt(page_select), sort));
        }
        return communities.stream().map(CommunityResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommunityResponseDto> findAllCommunityByNoticeContentOrderBy(String search_keyword, String order_select, String page_select){
        String[] orderSelect = order_select.split("_");
        Sort sort = Sort.by(orderSelect[1].equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, orderSelect[0]);
        List<Community> communities;
        if(page_select.equals("all")){
            communities = communityRepository.findAllCommunityByNoticeContent(search_keyword, sort);
        }else{
            communities = communityRepository.findAllCommunityByNoticeContentLimit(search_keyword, PageRequest.of(0, Integer.parseInt(page_select), sort));
        }
        return communities.stream().map(CommunityResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommunityResponseDto> findAllCommunityByNoticeMemberIdOrderBy(String search_keyword, String order_select, String page_select){
        String[] orderSelect = order_select.split("_");
        Sort sort = Sort.by(orderSelect[1].equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, orderSelect[0]);
        List<Community> communities;
        if(page_select.equals("all")){
            communities = communityRepository.findAllCommunityByNoticeMemberId(search_keyword, sort);
        }else{
            communities = communityRepository.findAllCommunityByNoticeMemberIdLimit(search_keyword, PageRequest.of(0, Integer.parseInt(page_select), sort));
        }
        return communities.stream().map(CommunityResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommunityResponseDto> findAllCommunityOrderBy(String order_select, String page_select){
        String[] orderSelect = order_select.split("_");
        Sort sort = Sort.by(orderSelect[1].equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, orderSelect[0]);
        List<Community> communities;
        if(page_select.equals("all")){
            communities = communityRepository.findAll(sort);
        }else{
            communities = communityRepository.findAllLimit(PageRequest.of(0, Integer.parseInt(page_select), sort));
        }
        return communities.stream().map(CommunityResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CommunityResponseDto getCommunity(Integer notice_idx){
        Community community = communityRepository.findById(notice_idx)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시글입니다."));
        return CommunityResponseDto.builder().entity(community).build();
    }

    @Transactional
    public Integer communitySave(CommunitySaveRequestDto dto){
        Community community = communityRepository.save(dto.toEntity());
        return community.getNoticeIdx();
    }

    @Transactional
    public Community communityUpdate(Integer noticeIdx, CommunityUpdateRequestDto dto){
        Community community = communityRepository.findById(noticeIdx)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시글입니다."));
        community.update(dto.getEditor4());
        return community;
    }

    // @Transactional
    // public void deleteNotice(Integer notice_idx){
    //     communityRepository.deleteById(notice_idx);
    // }

    @Transactional(readOnly = true)
    public boolean existsByNoticeIdx(Integer notice_idx){
        return communityRepository.existsByNoticeIdx(notice_idx);
    }
}
