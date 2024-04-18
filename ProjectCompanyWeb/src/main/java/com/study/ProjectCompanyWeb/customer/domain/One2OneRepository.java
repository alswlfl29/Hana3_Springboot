package com.study.ProjectCompanyWeb.customer.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface One2OneRepository extends JpaRepository<One2One, Integer> {
    Boolean existsByOne2oneIdx(Integer oneToOneIdx);
}
