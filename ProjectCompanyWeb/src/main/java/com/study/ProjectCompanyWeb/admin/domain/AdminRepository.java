package com.study.ProjectCompanyWeb.admin.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Boolean existsByMemberIdAndMemberPw(String memberId, String memberPw);
}
