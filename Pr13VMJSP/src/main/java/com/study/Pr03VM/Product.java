package com.study.Pr03VM;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private String name; // 상품명
    private int price; // 가격
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate limit_date; // 유통기한
}
