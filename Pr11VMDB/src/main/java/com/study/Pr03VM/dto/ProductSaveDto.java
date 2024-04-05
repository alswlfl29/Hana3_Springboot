package com.study.Pr03VM.dto;

import com.study.Pr03VM.entity.ProductEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSaveDto {
    private Long id;
    private String name;
    private int price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate limitDate;

    public ProductEntity toSaveEntity(){
        return ProductEntity.builder()
                .name(name)
                .price(price)
                .limitDate(limitDate)
                .build();
    }

    public ProductEntity toUpdateEntity(){
        return ProductEntity.builder()
                .id(id)
                .name(name)
                .price(price)
                .limitDate(limitDate)
                .build();
    }
}
