package com.study.Ex14ReadDB.entity;

import com.study.Ex14ReadDB.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    @Query(value = "select * from member m where m.user_id = :userId", nativeQuery = true)
    Optional<MemberEntity> findByUserId(String userId);

    @Query(value = "select * from member m where m.user_id = :userId "+ "and m.user_pw=:userPw", nativeQuery = true)
    Optional<MemberEntity> findByUserIdAndUserPw(String userId, String userPw);

    // 위의 처럼 userId, userPw 말고 다른 이름으로 사용하고 싶을 경우 @Param 사용
    // @Query(value = "select * from member m where m.user_id = :param_user_id "+ "and m.user_pw=:param_user_pw", nativeQuery = true)
    // List<MemberEntity> findByUserIdAndUserPw(@Param("param_user_id") String userId, @Param("param_user_pw") String userPw);
}
