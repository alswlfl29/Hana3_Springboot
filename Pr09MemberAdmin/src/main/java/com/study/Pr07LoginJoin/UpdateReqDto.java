package com.study.Pr07LoginJoin;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class UpdateReqDto {
    private int index;
    private String inputName;
    private String inputPw;
    private String inputEmail;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate inputJoindate;
}
