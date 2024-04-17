package com.study.Ex14ReadDB.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    // 기본함수: findAll, findById, save, delete
}
