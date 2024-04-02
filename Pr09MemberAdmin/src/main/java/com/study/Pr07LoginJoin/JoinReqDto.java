package com.study.Pr07LoginJoin;

import lombok.Data;

@Data
public class JoinReqDto {
    private String inputName;
    private String inputEmail;
    private String inputPw;
    private String inputPw2;
}
