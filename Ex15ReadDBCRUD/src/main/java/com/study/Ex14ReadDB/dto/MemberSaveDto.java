package com.study.Ex14ReadDB.dto;

import com.study.Ex14ReadDB.entity.MemberEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

// html 입력폼 <-> DTO(Data Transfer Object) <-> DAO(Data Access Object)[Entity] <-> Repo 클래스 <-> DB
// DTO에서 Entity로 변환해주는 메소드, Entity에서 DTO로 변환해주는 메소드를 만들어두면 좋음

// DTO 에는 DB에 들어가지 않는 필드명이 존재할 수 있다.
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor // @AllArgsConstructor 가 있으면, @NoArgsConstructor 주의해야함!
@Builder
public class MemberSaveDto {
    private Long id;
    private String userId;
    private String userPw;
    private String userName;
    private String userRole;
    private String userAddress; // 입력폼에는 있고, DB 에는 없는 열
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate joinDate;

    // DTO의 값을 save용 Entity 로 변환해주는 메소드
    public MemberEntity toSaveEntity(){
        return MemberEntity.builder()
                .userId(userId)
                .userPw(userPw)
                .userName(userName)
                .userRole(userRole)
                .joinDate(joinDate)
                .build();
    }

    // DTO의 값을 update용 Entity로 변환해주는 메소드
    public MemberEntity toUpdateEntity(){
        return MemberEntity.builder()
                .id(id)
                .userId(userId)
                .userPw(userPw)
                .userName(userName)
                .userRole(userRole)
                .joinDate(joinDate)
                .build();
    }
}
