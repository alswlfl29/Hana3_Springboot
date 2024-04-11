package com.study.Pr03VM.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private int price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate limitDate;
}
