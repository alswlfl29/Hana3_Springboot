package com.study.Pr07LoginJoin;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateReqDto {
    private int index;
    private String inputName;
    private String inputPw;
    private String inputEmail;
    private LocalDate inputJoindate;
}
