package com.study.Pr03VM.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "product")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name="price")
    private int price;
    @Column(name="limit_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate limitDate;

    public void setCurrentDate(){
        if(this.limitDate == null){
            this.limitDate = LocalDate.now();
        }
    }
}
