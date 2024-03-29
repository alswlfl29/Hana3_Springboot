package com.study.Pr06VMAPI;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@Component
public class Product {
    private String name; // 상품명
    private int price; // 가격
    private LocalDate limit_date; // 유통기한
}