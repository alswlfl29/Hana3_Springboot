package com.study.Pr06VMAPI;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Data
public class ReqDto {
    private String name;
    private int price;
    private LocalDate limit_date;
}
