package com.study.ProjectCompanyWeb.community.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Integer> {
    @Query(value="SELECT c FROM Community c WHERE c.noticeTitle Like %:noticeTitle% ORDER BY c.noticeDate DESC, c.noticeIdx")
    List<Community> findByNoticeTitle(String noticeTitle);

    @Query(value="SELECT c FROM Community c WHERE c.noticeContent Like %:noticeContent% ORDER BY c.noticeDate DESC, c.noticeIdx")
    List<Community> findByNoticeContent(String noticeContent);
}
