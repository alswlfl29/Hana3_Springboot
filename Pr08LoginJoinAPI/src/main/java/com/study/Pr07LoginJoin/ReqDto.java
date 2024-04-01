package com.study.Pr07LoginJoin;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ReqDto {
    private String inputName;
    private String inputEmail;
    private String inputPw;
    private String inputPw2;
}
