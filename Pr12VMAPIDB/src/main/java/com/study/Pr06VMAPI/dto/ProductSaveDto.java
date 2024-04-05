package com.study.Pr06VMAPI.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.Pr06VMAPI.entity.ProductEntity;
import lombok.*;
import org.springframework.stereotype.Component;

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
    @JsonFormat(pattern = "yyyy-MM-dd")
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
