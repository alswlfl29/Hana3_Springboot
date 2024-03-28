package com.study.Ex09RestAPI;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder // 클래스 객체 생성을 쉽게 도와주는 기능을 가진 어노테이션
public class Member {
    private String username;
    private String password;
    private String email;
    private LocalDate joinData;

}
