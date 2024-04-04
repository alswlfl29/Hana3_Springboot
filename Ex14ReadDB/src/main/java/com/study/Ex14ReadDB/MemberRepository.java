package com.study.Ex14ReadDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
// JpaRepository<엔티티명, key 값의 타입>
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // 기본메소드
    // 1. findAll: select
    // 2. findById: select + where id=:id
    // 3. count(): select count(*)
    // 4. save(): update, insert
    // 5. delete(): delete

    // 쿼리 메소드, JPQL, NativeSQL 중 방식 선택해서 아래에 등록
}
