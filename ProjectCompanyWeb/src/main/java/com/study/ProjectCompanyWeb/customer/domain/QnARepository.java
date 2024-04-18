package com.study.ProjectCompanyWeb.customer.domain;

import com.study.ProjectCompanyWeb.community.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QnARepository extends JpaRepository<QnA, Integer> {
    @Query(value="SELECT q FROM QnA q WHERE q.qnaTitle Like %:qnaTitle% ORDER BY q.qnaDate DESC, q.qnaIdx")
    List<QnA> findByQnaTitle(String qnaTitle);

    @Query(value="SELECT q FROM QnA q WHERE q.qnaContent Like %:qnaContent% ORDER BY q.qnaDate DESC, q.qnaIdx")
    List<QnA> findByQnaContent(String qnaContent);

    @Query(value="SELECT q FROM QnA q WHERE q.qnaName Like %:qnaName% ORDER BY q.qnaDate DESC, q.qnaIdx")
    List<QnA> findByQnaName(String qnaName);

    Boolean existsByQnaIdxAndQnaPw(Integer qnaIdx, String qnaPw);
}
