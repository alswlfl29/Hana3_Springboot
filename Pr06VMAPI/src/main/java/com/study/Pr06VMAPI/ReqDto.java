package com.study.Pr06VMAPI;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Data
public class ReqDto {
    private String name;
    private int price;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate limit_date;
}
