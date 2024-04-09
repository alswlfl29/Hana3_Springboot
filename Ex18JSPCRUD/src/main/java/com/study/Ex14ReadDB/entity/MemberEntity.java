package com.study.Ex14ReadDB.entity;

import com.study.Ex14ReadDB.dto.MemberSaveDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name="member")
// @Data // 가벼운 DTO에서는 사용해도 괜찮지만, 위험하니까 적절하게 사용해야함
@Getter
// @Setter // 자체 setter 메소드를 사용하여 잘못된 제어가 들어가지 않도록 한다.
@AllArgsConstructor // 모든 매개변수가 있는 생성자
@NoArgsConstructor // 매개변수가 없는 생성자 // @ModelAttribute @RequestBody 매핑 시 필요
@Builder
public class MemberEntity {
    @Id
    // IDENTITY: MySQL, MariaDB, H2
    // SEQUENCE: Oracle, PostgreSQL, H2
    // AUTO: Hibernate에게 위임 -> 믿어서는 안됨(직접 DBMS에 맞는 전략 넣어주는게 좋음)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_pw")
    private String userPw;
    @Column(name = "user_name")
    private String userName;
    @Column(name="user_role")
    private String userRole;
    @Column(name="join_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate joinDate;

    // Entity의 값을 DTO로 변환해주는 메소드
    public MemberSaveDto toSaveDto(){
        return MemberSaveDto.builder()
                .id(id)
                .userId(userId)
                .userPw(userPw)
                .userName(userName)
                .userRole(userRole)
                .joinDate(joinDate)
                .build();
    }
}
