package com.study.ProjectCompanyWeb.community.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Integer> {
    Boolean existsByNoticeIdx(Integer noticeIdx);

    @Query(value="SELECT c FROM Community c " +
            "WHERE UPPER(c.noticeTitle) Like UPPER('%' || :searchKeyword || '%') OR UPPER(c.noticeContent) Like UPPER('%' || :searchKeyword || '%') OR UPPER(c.noticeMemberId) Like UPPER('%' || :searchKeyword || '%')")
    List<Community> findAllCommunityByNone(String searchKeyword, Sort sort);

    @Query(value="SELECT c FROM Community c WHERE UPPER(c.noticeTitle) Like UPPER('%' || :searchKeyword || '%')")
    List<Community> findAllCommunityByNoticeTitle(String searchKeyword, Sort sort);

    @Query(value="SELECT c FROM Community c WHERE UPPER(c.noticeContent) Like UPPER('%' || :searchKeyword || '%')")
    List<Community> findAllCommunityByNoticeContent(String searchKeyword, Sort sort);

    @Query(value="SELECT c FROM Community c WHERE UPPER(c.noticeMemberId) Like UPPER('%' || :searchKeyword || '%')")
    List<Community> findAllCommunityByNoticeMemberId(String searchKeyword, Sort sort);

    @Query(value="SELECT c FROM Community c " +
            "WHERE UPPER(c.noticeTitle) Like UPPER('%' || :searchKeyword || '%') OR UPPER(c.noticeContent) Like UPPER('%' || :searchKeyword || '%') OR UPPER(c.noticeMemberId) Like UPPER('%' || :searchKeyword || '%')")
    List<Community> findAllCommunityByNoneLimit(String searchKeyword, Pageable pageable);

    @Query(value="SELECT c FROM Community c WHERE UPPER(c.noticeTitle) Like UPPER('%' || :searchKeyword || '%')")
    List<Community> findAllCommunityByNoticeTitleLimit(String searchKeyword, Pageable pageable);

    @Query(value="SELECT c FROM Community c WHERE UPPER(c.noticeContent) Like UPPER('%' || :searchKeyword || '%')")
    List<Community> findAllCommunityByNoticeContentLimit(String searchKeyword, Pageable pageable);

    @Query(value="SELECT c FROM Community c WHERE UPPER(c.noticeMemberId) Like UPPER('%' || :searchKeyword || '%')")
    List<Community> findAllCommunityByNoticeMemberIdLimit(String searchKeyword, Pageable pageable);

    @Query(value="SELECT c FROM Community c")
    List<Community> findAllLimit(Pageable pageable);
}
