package com.study.ProjectCompanyWeb.customer.domain;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QnARepository extends JpaRepository<QnA, Integer> {
    @Query(value="SELECT q FROM QnA q WHERE UPPER(q.qnaTitle) Like UPPER('%' || :searchKeyword || '%')")
    List<QnA> findAllQnAByQnATitle(String searchKeyword, Sort sort);

    @Query(value="SELECT q FROM QnA q WHERE UPPER(q.qnaContent) Like UPPER('%' || :searchKeyword || '%')")
    List<QnA> findAllQnAByQnAContent(String searchKeyword, Sort sort);

    @Query(value="SELECT q FROM QnA q WHERE UPPER(q.qnaName) Like UPPER('%' || :searchKeyword || '%')")
    List<QnA> findAllQnAByQnAName(String searchKeyword, Sort sort);

    Boolean existsByQnaIdxAndQnaPw(Integer qnaIdx, String qnaPw);
}
