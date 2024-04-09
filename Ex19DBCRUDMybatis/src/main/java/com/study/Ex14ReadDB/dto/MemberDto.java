package com.study.Ex14ReadDB.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

// html 입력폼 <-> DTO <-> DAO(Entity) <-> XML(Repo 클래스) <-> DB
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    private Long id;
    private String userId;
    private String userPw;
    private String userName;
    private String userRole;
    private String userAddress;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate joinDate;

}
