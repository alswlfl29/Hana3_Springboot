package com.study.Ex17JSP;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private int userNo;
    private String userId;
    private String userPw;
    private String phone;
    private String address;
    private LocalDate regDate;

}
