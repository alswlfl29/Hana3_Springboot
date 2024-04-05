package com.study.Pr06VMAPI.entity;

import com.study.Pr06VMAPI.dto.ProductSaveDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "product")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private int price;
    @Column(name = "limit_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate limitDate;

    public ProductSaveDto toSaveProductDto(){
        return ProductSaveDto.builder()
                .id(id)
                .name(name)
                .price(price)
                .limitDate(limitDate)
                .build();
    }

    public void setLimitDate(){
        if(this.limitDate == null) {
            this.limitDate = LocalDate.now();
        }
    }
}
